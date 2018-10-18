package com.pieterjd.aemadmin.command.bundles;

public class StartBundleCommand extends AbstractBundleCommand {
    public StartBundleCommand(int bundleId) {
        super(bundleId);
    }

    @Override
    protected String getAction() {
        return "update";
    }
}
