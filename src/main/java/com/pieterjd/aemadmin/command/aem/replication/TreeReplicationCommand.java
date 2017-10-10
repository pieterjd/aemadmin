package com.pieterjd.aemadmin.command.aem.replication;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdrouill on 1/09/2017.
 */
public abstract class TreeReplicationCommand extends ReplicationCommand {
    private boolean ignoreDeactivated;
    private boolean onlyModified;
    public TreeReplicationCommand(String command, String path){
        this(command,path,true,true);
    }
    public TreeReplicationCommand(String command, String path,boolean ignoreDeactivated,boolean onlyModified) {
        super(command, path);
        setIgnoreDeactivated(ignoreDeactivated);
        setOnlyModified(onlyModified);
    }

    public boolean isIgnoreDeactivated() {
        return ignoreDeactivated;
    }

    public void setIgnoreDeactivated(boolean ignoreDeactivated) {
        this.ignoreDeactivated = ignoreDeactivated;
    }

    public boolean isOnlyModified() {
        return onlyModified;
    }

    public void setOnlyModified(boolean onlyModified) {
        this.onlyModified = onlyModified;
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("path",getPath()));
        params.add(new BasicNameValuePair("cmd",getCommand()));
        params.add(new BasicNameValuePair("ignoredeactivated",Boolean.toString(isIgnoreDeactivated())));
        params.add(new BasicNameValuePair("onlymodified",Boolean.toString(isOnlyModified())));
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedGetRequestBuilder("/etc/replication/treeactivation.html")
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
