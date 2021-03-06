package com.pieterjd.aemadmin.command.packmgr;

import org.apache.http.client.methods.HttpUriRequest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Installs a package that is already uploaded.
 *
 * If you want to upload a package and install it, see {@link UploadPackageCommand}.
 */
public class InstallPackageCommand extends PackageMgrCommand {
    /**
     * @param packageName the complete package name as found under etc/packages, eg my_packages/mvc-5338.zip
     */
    public InstallPackageCommand(String packageName) {
        super(packageName);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedPostRequestBuilder("/crx/packmgr/service/.json/etc/packages/"+getPackageName()+"?cmd=install").build();
    }


    @Override
    public void execute() {
        File f = new File(getPackageName());
        System.out.println(String.format("Installing package %s",f.getName()));
        super.execute();
        try {
            boolean installSuccess = getHttpResponseAsJSON().getBoolean("success");
            setSuccess(isSuccess() && installSuccess);
        } catch (IOException e) {

        }

    }
}
