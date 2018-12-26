package com.pieterjd.aemadmin.shell.commands;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.QueryCommand;
import com.pieterjd.aemadmin.service.QueryService;
import com.pieterjd.aemadmin.shell.utils.BundleJsonTableModel;
import com.pieterjd.aemadmin.shell.utils.JsonTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ShellComponent
public class QueryCommands {
    @Autowired
    private QueryService queryService;


    @ShellMethod("AEM Query Builder API. Conditions are comma separated")
    public Table query(@ShellOption String criteria){
        List<JSONObject> hits = queryService.query(criteria);

        TableModel model = new JsonTableModel(hits);
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.fancy_light)
                .build();
    }
}
