package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.packmgr.BuildPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.DownloadPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.InstallPackageCommand;
import com.pieterjd.aemadmin.command.packmgr.ListPackagesCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageServiceImpl implements PackageService {

    @Override
    public List<JSONObject> listPackages() throws IOException {
        ListPackagesCommand c = new ListPackagesCommand();
        c.execute();

        List<JSONObject> packages = new ArrayList<>();
        JSONArray data = c.getHttpResponseAsJSON()
                .getJSONObject("crx").getJSONObject("response")
                .getJSONObject("data").getJSONObject("packages").getJSONArray("package");
        for(int i=0;i<data.length();i++){
            packages.add(data.getJSONObject(i));
        }
        return packages;
    }


    @Override
    public String installPackage(File packageName) throws IOException {
        InstallPackageCommand c = new InstallPackageCommand(packageName.getPath());
        c.execute();
        return c.getHttpResponseAsJSON().toString(1);
    }


    @Override
    public String buildPackage(String packageName) throws IOException {
        BuildPackageCommand c = new BuildPackageCommand(packageName);
        c.execute();
        return c.getHttpResponseAsString();
    }


    @Override
    public String downloadPackage(String packageName, String fileName) throws IOException {
        DownloadPackageCommand c = new DownloadPackageCommand(packageName,fileName);
        c.execute();
        return c.getHttpResponseAsString();
    }
}
