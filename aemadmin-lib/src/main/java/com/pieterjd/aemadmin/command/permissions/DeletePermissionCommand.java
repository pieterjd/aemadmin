package com.pieterjd.aemadmin.command.permissions;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deletes all permission for a specific principalId
 */
public class DeletePermissionCommand extends SetPermissionCommand {
    public DeletePermissionCommand(String path, String principalId) {
        super(path, principalId, null);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(":applyTo",getPrincipalId()));

        try {
            result = getAuthenticatedPostRequestBuilder(getPath()+".deleteAce.json")
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
