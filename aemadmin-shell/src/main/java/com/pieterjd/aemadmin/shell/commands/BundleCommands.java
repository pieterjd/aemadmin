package com.pieterjd.aemadmin.shell.commands;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;
import com.pieterjd.aemadmin.command.bundles.*;
import com.pieterjd.aemadmin.shell.utils.JsonTableModel;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class BundleCommands {
    @ShellMethod("List all bundles")
    public Table listBundles() throws IOException {
        StatusBundlesCommand c = new StatusBundlesCommand();
        c.execute();
        //get jsonobject
        List<JSONObject> bundles = new ArrayList<>();
        JSONArray data = c.getHttpResponseAsJSON().getJSONArray("data");
        for(int i=0;i<data.length();i++){
            bundles.add(data.getJSONObject(i));
        }
        TableModel model = new JsonTableModel(bundles,"id","name","version","state");
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.air)
                .build();
        //return c.getHttpResponseAsJSON().toString(1);
    }

    @ShellMethod("Start a bundle")
    public String startBundle(String bundleId) throws IOException {
        StartBundleCommand c = new StartBundleCommand(Integer.parseInt(bundleId));
        c.execute();
        return c.toString();
    }


    @ShellMethod("Stop a bundle")
    public String stopBundle(String bundleId) throws IOException {
        StopBundleCommand c = new StopBundleCommand(Integer.parseInt(bundleId));
        c.execute();
        return c.toString();
    }

    @ShellMethod("Update a bundle")
    public String updateBundle(String bundleId) throws IOException {
        UpdateBundleCommand c = new UpdateBundleCommand(Integer.parseInt(bundleId));
        c.execute();
        return c.toString();
    }

    @ShellMethod("Refresh a bundle")
    public String refreshBundle(String bundleId) throws IOException {
        RefreshBundleCommand c = new RefreshBundleCommand(Integer.parseInt(bundleId));
        c.execute();
        return c.toString();
    }

    @ShellMethod("Search for a bundle containing a keyword in its name or symbolicName")
    public String searchBundle(String keyword,@ShellOption(defaultValue="false",help = "when this switch is enable, only bundle ids are returned")boolean id) throws IOException {
        SearchBundleCommand c = new SearchBundleCommand(keyword);
        c.execute();
        String result = c.getHits().stream()
                .map(b -> b.toString(1))
                .reduce("",(x,y) -> x+y);
        if(id){
            result = c.getHits().stream()
                    .map(b -> b.getInt("id"))
                    .collect(Collectors.toList())
            .toString();
        }
        return result;
    }

    @ShellMethod("Get a bundle using the bundle's id")
    public String getBundle(String bundleId) throws IOException {
        StatusBundlesCommand c = new StatusBundlesCommand();
        c.execute();
        return c.getBundles().stream()
                .filter(b -> b.getInt("id") == Integer.parseInt(bundleId))
                .findFirst().orElseGet(() -> new JSONObject())
                .toString(1)
                ;

    }

}