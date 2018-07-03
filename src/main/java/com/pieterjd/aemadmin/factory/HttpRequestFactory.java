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
    public HttpUriRequest getSetPropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException;
    public HttpUriRequest getDeletePropertyHttpRequest(String path, String propertyName) throws URISyntaxException;
    public HttpUriRequest getGetPropertyHttpRequest(String path,String propertyName) throws URISyntaxException;
    //multivalue http request
    public HttpUriRequest getAddToMultiValuePropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException;
    public HttpUriRequest getRemoveFromMultiValuePropertyHttpRequest(String path, String propertyName, String propertyValue) throws URISyntaxException;

    public Properties getProperties();
}
