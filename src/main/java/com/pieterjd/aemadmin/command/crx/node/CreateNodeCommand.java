package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdrouill on 3/07/2017.
 */
public class CreateNodeCommand extends CrxCommand {
    private String primaryType;
    public CreateNodeCommand(String path,String primaryType) {
        super(path);
        setPrimaryType(primaryType);
    }



    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("jcr:primaryType",getPrimaryType()));

        try {
            result = getAuthenticatedPostRequestBuilder(getPath())
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
