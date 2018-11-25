package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONArray;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
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

    public JSONArray getBundles() throws IOException {
        return getHttpResponseAsJSON().getJSONArray("data");
    }



}
