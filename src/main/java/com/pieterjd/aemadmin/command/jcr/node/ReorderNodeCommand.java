package com.pieterjd.aemadmin.command.jcr.node;

import com.pieterjd.aemadmin.command.jcr.JcrCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class ReorderNodeCommand extends JcrCommand {
    private String siblingNodename;
    /**
     * Path of the jcr node. this path should start with a "/"
     *
     * @param path the path to the node that will be moved
     * @param siblingNodename the node after/before/... which the node at path will be moved
     */
    public ReorderNodeCommand(String path,String siblingNodename) {
        super(path);
        setSiblingNodename(siblingNodename);

    }

    public String getSiblingNodename() {
        return siblingNodename;
    }

    public void setSiblingNodename(String siblingNodename) {
        this.siblingNodename = siblingNodename;
    }


    /**
     * Returns the value for the order parameter posted to @{@link #getPath()}
     * @return String being in the format as defined at
     * https://sling.apache.org/documentation/bundles/manipulating-content-the-slingpostservlet-servlets-post.html#order
     */
    protected abstract String  getOrderValue();

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(":order",getOrderValue()));

        try {
            result = getAuthenticatedPostRequestBuilder(getPath())
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("path",getPath())
                .append("siblingNode",getSiblingNodename());
    }
}
