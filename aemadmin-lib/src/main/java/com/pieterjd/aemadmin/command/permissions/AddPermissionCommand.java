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
 * Adds a permission for a given principalId. If the permission already exists for the given principalId, then
 * the permission is updated.
 */
public class AddPermissionCommand extends SetPermissionCommand {
    public AddPermissionCommand(String path, String principalId, JCRPermission permission) {
        super(path, principalId,permission);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpUriRequest result = null;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("principalId",getPrincipalId()));
        params.add(new BasicNameValuePair("privilege@"+getPermission(),"granted"));

        try {
            result = getAuthenticatedPostRequestBuilder(getPath()+".modifyAce.json")
                    .setEntity(new UrlEncodedFormEntity(params))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
