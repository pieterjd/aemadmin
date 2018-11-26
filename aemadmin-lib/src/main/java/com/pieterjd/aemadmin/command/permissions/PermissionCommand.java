package com.pieterjd.aemadmin.command.permissions;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.crx.CrxCommand;

public abstract class PermissionCommand extends CrxCommand {

    public PermissionCommand(String path){
        super(path);
    }

}
