package com.pieterjd.aemadmin.shell.commands;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.packmgr.BuildPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.DownloadPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.InstallPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.ListPackagesCommand;
import com.pieterjd.aemadmin.shell.utils.DirectoryValueProvider;
import com.pieterjd.aemadmin.shell.utils.JsonTableModel;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class PackageCommands {
    @ShellMethod("List all packages")
    public Table listPackages() throws IOException {
        ListPackagesCommand c = new ListPackagesCommand();
        c.execute();

        List<JSONObject> packages = new ArrayList<>();
        JSONArray data = c.getHttpResponseAsJSON()
                .getJSONObject("crx").getJSONObject("response")
                .getJSONObject("data").getJSONObject("packages").getJSONArray("package");
        for(int i=0;i<data.length();i++){
            packages.add(data.getJSONObject(i));
        }
        TableModel model = new JsonTableModel(packages);
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.air)
                .build();
    }

    @ShellMethod("Installs a package")
    public String installPackage(@ShellOption(valueProvider = DirectoryValueProvider.class) File packageName) throws IOException {
        InstallPackageCommand c = new InstallPackageCommand(packageName.getPath());
        c.execute();
        return c.getHttpResponseAsJSON().toString(1);
    }

    @ShellMethod("Builds a package")
    public String buildPackage(String  packageName) throws IOException {
        BuildPackageCommand c = new BuildPackageCommand(packageName);
        c.execute();
        return c.getHttpResponseAsString();
    }

    @ShellMethod("Downloads a package")
    public String downloadPackage(String  packageName,String fileName) throws IOException {
        DownloadPackageCommand c = new DownloadPackageCommand(packageName,fileName);
        c.execute();
        return c.getHttpResponseAsString();
    }
}
