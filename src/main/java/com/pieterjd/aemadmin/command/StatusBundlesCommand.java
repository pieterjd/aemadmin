package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

    public List<JSONObject> getBundles() throws IOException{
        JSONArray bundles = getHttpResponseAsJSON().getJSONArray("data");;
        List<JSONObject> result = new ArrayList<>();
        for(int i = 0;i<bundles.length();i++){
            result.add(bundles.getJSONObject(i));
        }
        return result;
    }



}
