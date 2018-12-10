package com.pieterjd.aemadmin.shell.commands;

import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.service.AEMAdminService;
import com.pieterjd.aemadmin.shell.utils.BundleJsonTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import java.io.IOException;
import java.util.List;

@ShellComponent
public class BundleCommands {

    @Autowired
    private AEMAdminService service;


    @ShellMethod("List all bundles")
    public Table listBundles() throws IOException {
        List<JSONObject> bundles = service.getBundles();
        TableModel model = new BundleJsonTableModel(bundles);
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.fancy_light)
                .build();
        //return c.getHttpResponseAsJSON().toString(1);
    }

    @ShellMethod("Start a bundle")
    public String startBundle(@ShellOption(value={"bundleId","id"}) String bundleId) throws IOException {
        return service.startBundle(Integer.parseInt(bundleId));
    }


    @ShellMethod("Stop a bundle")
    public String stopBundle(@ShellOption(value={"bundleId","id"}) String bundleId) throws IOException {
        return service.stopBundle(Integer.parseInt(bundleId));
    }

    @ShellMethod("Update a bundle")
    public String updateBundle(@ShellOption(value={"bundleId","id"}) String bundleId) throws IOException {
        return service.updateBundle(Integer.parseInt(bundleId));
    }

    @ShellMethod("Refresh a bundle")
    public String refreshBundle(@ShellOption(value={"bundleId","id"}) String bundleId) throws IOException {
        return service.refreshBundle(Integer.parseInt(bundleId));
    }

    @ShellMethod("Search for a bundle containing a keyword in its name or symbolicName")
    public Table searchBundle(String keyword) throws IOException {
        List<JSONObject> hits = service.searchBundle(keyword);

        TableModel model = new BundleJsonTableModel(hits);
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.fancy_light)
                .build();
    }

    @ShellMethod("Get a bundle using the bundle's id")
    public String getBundle(@ShellOption(value={"bundleId","id"}) String bundleId) throws IOException {
        return service.getBundle(Integer.parseInt(bundleId));

    }

}
