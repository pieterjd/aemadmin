package com.pieterjd.aemadmin.command.packmgr;

import com.pieterjd.aemadmin.command.HttpRequestCommand;

/**
 * Created by pdrouill on 29/08/2017.
 */
public abstract class PackageMgrCommand extends HttpRequestCommand {
    private String packageName;

    public PackageMgrCommand(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
