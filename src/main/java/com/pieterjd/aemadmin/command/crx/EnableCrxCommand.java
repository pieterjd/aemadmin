package com.pieterjd.aemadmin.command.crx;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class EnableCrxCommand extends HttpRequestCommand {
    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("jcr:primaryType","sling:OsgiConfig"));
        params.add(new BasicNameValuePair("alias","/crx/server"));
        params.add(new BasicNameValuePair("dav.create-absolute-uri","true"));
        params.add(new BasicNameValuePair("dav.create-absolute-uri@TypeHint","Boolean"));
        HttpUriRequest result = null;
        try {
            result =  getAuthenticatedPostRequestBuilder("/apps/system/config/org.apache.sling.jcr.davex.impl.servlets.SlingDavExServlet")
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
