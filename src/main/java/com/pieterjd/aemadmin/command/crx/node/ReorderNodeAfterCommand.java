package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdrouill on 11/07/2017.
 */
public class ReorderNodeAfterCommand extends CrxCommand{
    private String putAfterNode;
    public ReorderNodeAfterCommand(String path,String putAfterNode) {
        super(path);
        setPutAfterNode(putAfterNode);
    }



    public String getPutAfterNode() {
        return putAfterNode;
    }

    public void setPutAfterNode(String putAfterNode) {
        this.putAfterNode = putAfterNode;
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("path",getPath())
                .append("putAfterNode",getPutAfterNode());
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("order","after "+getPutAfterNode()));

        try {
            result = getAuthenticatedGetRequestBuilder(getPath())
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
