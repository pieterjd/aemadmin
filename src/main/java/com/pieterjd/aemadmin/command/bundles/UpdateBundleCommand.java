package com.pieterjd.aemadmin.command.bundles;

public class UpdateBundleCommand extends AbstractBundleCommand {
    public UpdateBundleCommand(int bundleId) {
        super(bundleId);
    }

    @Override
    protected String getAction() {
        return "stop";
    }
}
