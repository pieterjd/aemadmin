package com.pieterjd.aemadmin.factory;

import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AEMRequestFactory extends AbstractRequestFactory {
    @Override
    public HttpUriRequest getCreateNodeHttpRequest(String path, String primaryType) throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("jcr:primaryType",primaryType));

        try {
            result = getAuthenticatedPostRequestBuilder(path)
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public HttpUriRequest getDeleteNodeHttpRequest(String path) throws URISyntaxException {
        return getAuthenticatedDeleteRequestBuilder("/crx/server/crx.default/jcr:root"+path).build();
    }

    @Override
    public HttpUriRequest getGetNodeHttpRequest(String path,Integer depth) throws URISyntaxException {
        String uri = "/crx/server/crx.default/jcr:root"+path+".";
        if(depth != null){
            uri +=depth+".";
        }
        uri +="json";
        return getAuthenticatedGetRequestBuilder(uri).build();
    }

    @Override
    public HttpUriRequest getSetPropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(propertyName, propertyValue));
        if(propertyType != null){
            params.add(new BasicNameValuePair(propertyName +"@TypeHint",propertyType));
        }
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder(path)
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public HttpUriRequest getDeletePropertyHttpRequest(String path, String propertyName) throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(propertyName +"@Delete","some random value"));
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder(path)
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public HttpUriRequest getGetPropertyHttpRequest(String path, String propertyName) throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder(path+".json").build();
    }

    @Override
    public HttpUriRequest getAddToMultiValuePropertyHttpRequest(String path, String propertyName, String propertyValue, String propertyType) throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        if(propertyType != null){
            params.add(new BasicNameValuePair(propertyName+"@TypeHint",propertyType+"[]"));
        }
        params.add(new BasicNameValuePair(propertyName+"@Patch","true"));
        params.add(new BasicNameValuePair(propertyName,"+"+propertyValue));
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder(path)
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public HttpUriRequest getRemoveFromMultiValuePropertyHttpRequest(String path, String propertyName, String propertyValue) throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(propertyName+"@Patch","true"));
        params.add(new BasicNameValuePair(propertyName,"-"+propertyValue));
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder(path)
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
