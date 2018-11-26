package com.pieterjd.aemadmin.command.packmgr;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Created by pdrouill on 29/08/2017.
 */
public class BuildPackageCommand extends PackageMgrCommand {
    /**
     * @param packageName the complete package name as found under etc/packages, eg my_packages/mvc-5338.zip
     */
    public BuildPackageCommand(String packageName) {
        super(packageName);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedPostRequestBuilder("/crx/packmgr/service/.json/etc/packages/"+getPackageName()+"?cmd=build").build();
    }
}
