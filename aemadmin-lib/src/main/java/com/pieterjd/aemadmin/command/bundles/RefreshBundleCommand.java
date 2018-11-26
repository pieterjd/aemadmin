package com.pieterjd.aemadmin.command.bundles;

public class RefreshBundleCommand extends AbstractBundleCommand {
    public RefreshBundleCommand(int bundleId) {
        super(bundleId);
    }

    @Override
    protected String getAction() {
        return "refresh";
    }
}
