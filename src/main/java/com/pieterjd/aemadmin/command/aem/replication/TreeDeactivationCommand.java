package com.pieterjd.aemadmin.command.aem.replication;

/**
 * Created by pdrouill on 1/09/2017.
 */
public class TreeDeactivationCommand extends TreeReplicationCommand {
    public TreeDeactivationCommand(String path) {
        this(path,true,true);
    }

    public TreeDeactivationCommand(String path, boolean ignoreDeactivated, boolean onlyModified) {
        super("deactivate", path, ignoreDeactivated, onlyModified);
    }
}
