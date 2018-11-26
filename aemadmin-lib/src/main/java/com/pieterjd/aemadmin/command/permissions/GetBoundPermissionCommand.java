package com.pieterjd.aemadmin.command.permissions;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

public class GetBoundPermissionCommand extends PermissionCommand {
    public GetBoundPermissionCommand(String path) {
        super(path);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder(getPath()+".acl.json").build();
    }
}
