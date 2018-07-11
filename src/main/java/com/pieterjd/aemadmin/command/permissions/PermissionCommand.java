package com.pieterjd.aemadmin.command.permissions;

import com.pieterjd.aemadmin.command.jcr.JcrCommand;

public abstract class PermissionCommand extends JcrCommand {

    public PermissionCommand(String path){
        super(path);
    }

}
