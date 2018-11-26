package com.pieterjd.aemadmin.command.aem.replication;



/**
 * Created by pdrouill on 1/09/2017.
 */
public class TreeActivationCommand extends TreeReplicationCommand {

    /**
     * Create an object to do  tree activation. Deactivated pages are ignored and only
     * modified pages since last activation will be activated.
     *
     * @param path rootpath of pages to activate
     */
    public TreeActivationCommand(String path) {
        this(path,true,true);
    }

    /**
     * Fully customizable tree actviation
     * @param path rootpath of pages to activate
     * @param ignoreDeactivated if true: only re-activate the active pages; if false, activate all pages
     * @param onlyModified if true: only re-activate pages that have modified since the last activation; if false activate all pages
     */
    public TreeActivationCommand(String path, boolean ignoreDeactivated, boolean onlyModified) {
        super("activate", path, ignoreDeactivated, onlyModified);
    }
}
