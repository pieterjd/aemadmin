package com.pieterjd.aemadmin.command.bundles;

public class UninstallBundleCommand extends AbstractBundleCommand {
    public UninstallBundleCommand(int bundleId) {
        super(bundleId);
    }

    @Override
    protected String getAction() {
        return "uninstall";
    }
}
