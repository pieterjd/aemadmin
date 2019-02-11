package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Interface to the available AEM Admin commands. It's main purpose is to expose
 * functionality on a higher level for common usages such as getting a list of bundles,
 * querying, ....
 */


public interface BundleService {

    // bundle methods

    List<JSONObject> getBundles() throws IOException;
    String startBundle(int bundleId);
    String stopBundle(int bundleId);
    String updateBundle(int bundleId);
    String refreshBundle(int bundleId);
    String getBundle(int bundleId) throws IOException;
    List<JSONObject> searchBundle(String keyword);






}
