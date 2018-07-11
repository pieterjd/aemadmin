package com.pieterjd.aemadmin.factory;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;
import java.util.Properties;

public class AEMRequestFactoryImpl extends AbstractRequestFactory implements AEMHttpRequestFactory{
    private HttpRequestFactory slingRequestFactory;
    public AEMRequestFactoryImpl(){
        this.slingRequestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance(Platform.SLING);
    }

    @Override
    public HttpUriRequest getCreateNodeHttpRequest(String path, String primaryType) throws URISyntaxException {
        return slingRequestFactory.getCreateNodeHttpRequest(path, primaryType);
    }

    @Override
    public HttpUriRequest getDeleteNodeHttpRequest(String path) throws URISyntaxException {
        return slingRequestFactory.getDeleteNodeHttpRequest(path);
    }

    @Override
    public HttpUriRequest getGetNodeHttpRequest(String path, Integer depth) throws URISyntaxException {
        return slingRequestFactory.getGetNodeHttpRequest(path, depth);
    }

    @Override
    public HttpUriRequest getSetPropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException {
        return slingRequestFactory.getSetPropertyHttpRequest(path, propertyName, propertyValue, propertyType);
    }

    @Override
    public HttpUriRequest getDeletePropertyHttpRequest(String path, String propertyName) throws URISyntaxException {
        return slingRequestFactory.getDeletePropertyHttpRequest(path, propertyName);
    }

    @Override
    public HttpUriRequest getGetPropertyHttpRequest(String path, String propertyName) throws URISyntaxException {
        return slingRequestFactory.getGetPropertyHttpRequest(path, propertyName);
    }

    @Override
    public HttpUriRequest getAddToMultiValuePropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException {
        return slingRequestFactory.getAddToMultiValuePropertyHttpRequest(path, propertyName, propertyValue, propertyType);
    }

    @Override
    public HttpUriRequest getRemoveFromMultiValuePropertyHttpRequest(String path, String propertyName, String propertyValue) throws URISyntaxException {
        return slingRequestFactory.getRemoveFromMultiValuePropertyHttpRequest(path, propertyName, propertyValue);
    }

    @Override
    public HttpUriRequest getRefreshBundleHttpRequest(int bundleId) throws URISyntaxException {
        return slingRequestFactory.getRefreshBundleHttpRequest(bundleId);
    }

    @Override
    public HttpUriRequest getUninstallBundleHttpRequest(int bundleId) throws URISyntaxException {
        return slingRequestFactory.getUninstallBundleHttpRequest(bundleId);
    }

    @Override
    public HttpUriRequest getStatusBundlesHttpRequest() throws URISyntaxException {
        return slingRequestFactory.getStatusBundlesHttpRequest();
    }

    @Override
    public Properties getProperties() {
        return slingRequestFactory.getProperties();
    }
}
