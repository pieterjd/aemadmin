package com.pieterjd.aemadmin.factory;

import com.github.tsohr.HTTP;
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
            path +=depth+".";
        }
        path +="json";
        return getAuthenticatedGetRequestBuilder(uri).build();
    }
}
