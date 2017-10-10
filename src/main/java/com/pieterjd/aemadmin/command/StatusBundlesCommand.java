package com.pieterjd.aemadmin.command;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Created by pdrouill on 21/06/2017.
 */
public class StatusBundlesCommand extends HttpRequestCommand {
    public HttpUriRequest getRequest() {
        //checking url /system/console/status-Bundles.json
        HttpUriRequest result = null;
        try {
            result = getAuthenticatedGetRequestBuilder("/system/console/bundles.json").build();
        } catch (URISyntaxException e) {

        }
        return result;
    }




}
