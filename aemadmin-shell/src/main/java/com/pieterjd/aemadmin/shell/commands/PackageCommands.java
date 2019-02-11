package com.pieterjd.aemadmin.shell.commands;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.packmgr.BuildPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.DownloadPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.InstallPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.ListPackagesCommand;
import com.pieterjd.aemadmin.service.PackageService;
import com.pieterjd.aemadmin.shell.utils.DirectoryValueProvider;
import com.pieterjd.aemadmin.shell.utils.JsonTableModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PackageService service;

    @ShellMethod("List all packages")
    public Table listPackages() throws IOException {

        List<JSONObject> packages = service.listPackages();

        TableModel model = new JsonTableModel(packages);
        return new TableBuilder(model)
                .addFullBorder(BorderStyle.air)
                .build();
    }

    @ShellMethod("Installs a package")
    public String installPackage(@ShellOption(valueProvider = DirectoryValueProvider.class) File packageName) throws IOException {
        return service.installPackage(packageName);
    }

    @ShellMethod("Builds a package")
    public String buildPackage(String  packageName) throws IOException {
        return service.buildPackage(packageName);
    }

    @ShellMethod("Downloads a package")
    public String downloadPackage(String  packageName,String fileName) throws IOException {
        return service.downloadPackage(packageName,fileName);
    }
}
