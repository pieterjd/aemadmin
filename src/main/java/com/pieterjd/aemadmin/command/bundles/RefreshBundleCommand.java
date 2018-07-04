package com.pieterjd.aemadmin.command.bundles;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

public class RefreshBundleCommand extends AbstractBundleCommand {
    public RefreshBundleCommand(int bundleId) {
        super(bundleId);
    }


    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getRefreshBundleHttpRequest(getBundleId());
    }
}
