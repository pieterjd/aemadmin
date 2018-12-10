package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;
import com.pieterjd.aemadmin.command.bundles.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A default implementation of {@link AEMAdminService}
 */

public class AemAdminServiceImpl implements AEMAdminService {
    @Override
    public List<JSONObject> getBundles() throws IOException {
        StatusBundlesCommand c = new StatusBundlesCommand();
        c.execute();
        //get jsonobject
        List<JSONObject> bundles = new ArrayList<>();
        JSONArray data = c.getHttpResponseAsJSON().getJSONArray("data");
        for(int i=0;i<data.length();i++){
            bundles.add(data.getJSONObject(i));
        }
        return bundles;
    }

    @Override
    public String startBundle(int bundleId) {
        StartBundleCommand c = new StartBundleCommand(bundleId);
        c.execute();
        return c.toString();
    }

    @Override
    public String stopBundle(int bundleId) {
        StopBundleCommand c = new StopBundleCommand(bundleId);
        c.execute();
        return c.toString();
    }

    @Override
    public String updateBundle(int bundleId) {
        UpdateBundleCommand c = new UpdateBundleCommand(bundleId);
        c.execute();
        return c.toString();
    }

    @Override
    public String refreshBundle(int bundleId) {
        RefreshBundleCommand c = new RefreshBundleCommand(bundleId);
        c.execute();
        return c.toString();
    }

    @Override
    public String getBundle(int bundleId) throws IOException {
        StatusBundlesCommand c = new StatusBundlesCommand();
        c.execute();
        return c.getBundles().stream()
                .filter(b -> b.getInt("id") == bundleId)
                .findFirst().orElseGet(() -> new JSONObject())
                .toString(1)
                ;
    }

    @Override
    public List<JSONObject> searchBundle(String keyword) {
        SearchBundleCommand c = new SearchBundleCommand(keyword);
        c.execute();
        return c.getHits();
    }
}
