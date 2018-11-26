package com.pieterjd.aemadmin.command.permissions;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

public class GetEffectivePermissionCommand extends PermissionCommand {
    public GetEffectivePermissionCommand(String path) {
        super(path);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder(getPath()+".eacl.json").build();
    }
}
