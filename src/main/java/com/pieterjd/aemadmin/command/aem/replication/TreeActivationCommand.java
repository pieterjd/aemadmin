package com.pieterjd.aemadmin.command.aem.replication;



/**
 * Created by pdrouill on 1/09/2017.
 */
public class TreeActivationCommand extends TreeReplicationCommand {
    public TreeActivationCommand(String path) {
        this(path,true,true);
    }

    public TreeActivationCommand(String path, boolean ignoreDeactivated, boolean onlyModified) {
        super("activate", path, ignoreDeactivated, onlyModified);
    }
}
