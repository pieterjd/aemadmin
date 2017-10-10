package com.pieterjd.aemadmin.command.aem.replication;

/**
 * Created by pdrouill on 1/09/2017.
 */
public class DeactivatePageCommand extends ReplicationCommand {
    public DeactivatePageCommand(String path) {
        super("deactivate", path);
    }

}
