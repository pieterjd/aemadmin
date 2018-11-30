package com.pieterjd.aemadmin.shell.commands;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.QueryCommand;
import com.pieterjd.aemadmin.shell.utils.BundleJsonTableModel;
import com.pieterjd.aemadmin.shell.utils.JsonTableModel;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ShellComponent
public class QueryCommands {
    @ShellMethod("queries the JCR. Conditions are comma separated. For more information, please check  the AEM Query Builder API")
    public Table query(@ShellOption String criteria){
        QueryCommand c = new QueryCommand();
        Stream.of(criteria.split(","))
                .map(crit -> crit.split("="))
                .forEach(tuple ->c.addCondition(tuple[0],tuple[1]));
        c.execute();
        JSONArray array = c.getQueryResult().getJSONArray("hits");
        List<JSONObject> hits = new ArrayList<>();

        for(int i = 0;i<array.length();i++){
            hits.add(array.getJSONObject(i));
        }

        TableModel model = new JsonTableModel(hits);
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.fancy_light)
                .build();
    }
}
