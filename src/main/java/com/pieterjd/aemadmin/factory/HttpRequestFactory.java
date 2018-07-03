package com.pieterjd.aemadmin.factory;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;
import java.util.Properties;

public interface HttpRequestFactory {

    // node http request
    public HttpUriRequest getCreateNodeHttpRequest(String path,String primaryType) throws URISyntaxException;
    public HttpUriRequest getDeleteNodeHttpRequest(String path) throws URISyntaxException;
    public HttpUriRequest getGetNodeHttpRequest(String path,Integer depth) throws URISyntaxException;

    //properties http request
    public HttpRequest getSetPropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException;
    public HttpRequest getDeletePropertyHttpRequest(String path,String propertyName) throws URISyntaxException;
    public HttpRequest getGetPropertyHttpRequest(String path,String propertyName) throws URISyntaxException;


    public Properties getProperties();
}
