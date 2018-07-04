package com.pieterjd.aemadmin.command.bundles;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBundleCommand extends HttpRequestCommand {
    private int bundleId;
    public AbstractBundleCommand(int bundleId){
        setBundleId(bundleId);
    }

    public int getBundleId() {
        return bundleId;
    }

    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }


}
