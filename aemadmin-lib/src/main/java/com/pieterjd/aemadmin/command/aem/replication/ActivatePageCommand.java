package com.pieterjd.aemadmin.command.aem.replication;

/**
 * Created by pdrouill on 1/09/2017.
 */
public class ActivatePageCommand extends ReplicationCommand {
    public ActivatePageCommand(String path) {
        super("activate", path);
    }

}
