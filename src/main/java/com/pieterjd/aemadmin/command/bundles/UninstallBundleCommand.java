package com.pieterjd.aemadmin.command.bundles;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

public class UninstallBundleCommand extends AbstractBundleCommand {
    public UninstallBundleCommand(int bundleId) {
        super(bundleId);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getUninstallBundleHttpRequest(getBundleId());
    }
}
