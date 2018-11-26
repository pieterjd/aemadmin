package com.pieterjd.aemadmin.command.bundles;

public class StopBundleCommand extends AbstractBundleCommand {
    public StopBundleCommand(int bundleId) {
        super(bundleId);
    }

    @Override
    protected String getAction() {
        return "stop";
    }
}
