package com.pieterjd.aemadmin.command.bundles;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by pdrouill on 21/06/2017.
 */
public class StatusBundlesCommand extends HttpRequestCommand {
    public HttpUriRequest getRequest()  throws URISyntaxException{
        return getHttpRequestFactory().getStatusBundlesHttpRequest();
    }

    private int getBundleCount(int index) throws IOException {
        return getHttpResponseAsJSON().getJSONArray("s").getInt(index);
    }

    public int getTotalBundlesCount() throws IOException {
        return getBundleCount(0);
    }
    public int getActiveBundlesCount() throws IOException {
        return getBundleCount(1);
    }
    public int getActiveFragmentsCount() throws IOException {
        return getBundleCount(2);
    }
    public int getResolvedBundlesCount() throws IOException {
        return getBundleCount(3);
    }



}
