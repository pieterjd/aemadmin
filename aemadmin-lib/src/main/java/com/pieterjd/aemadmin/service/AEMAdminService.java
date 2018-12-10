package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Interface to the available AEM Admin commands. It's main purpose is to expose
 * functionality on a higher level for common usages such as getting a list of bundles,
 * querying, ....
 */


public interface AEMAdminService {

    // bundle methods

    public List<JSONObject> getBundles() throws IOException;
    public String startBundle(int bundleId);
    public String stopBundle(int bundleId);
    public String updateBundle(int bundleId);
    public String refreshBundle(int bundleId);
    public String getBundle(int bundleId) throws IOException;
    public List<JSONObject> searchBundle(String keyword);






}
