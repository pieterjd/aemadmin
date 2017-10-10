package com.pieterjd.aemadmin.command.packmgr;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Created by pdrouill on 29/08/2017.
 */
public class DownloadPackageCommand extends PackageMgrCommand {

    private String fileName;

    /**
     * @param packageName the complete package name as found under etc/packages, eg my_packages/MVC-5345.zip
     * @param fileName the file you want to save the content to
     */
    public DownloadPackageCommand(String packageName,String fileName) {
        super(packageName);
        this.fileName = fileName;
    }



    public String getFileName() {
        return fileName;
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder("/etc/packages/"+getPackageName()).build();
    }

    @Override
    public void execute() {
        super.execute();
        try {
            InputStream is = getHttpResponse().getEntity().getContent();
            FileOutputStream fos = new FileOutputStream(getFileName());
            IOUtils.copy(is,fos);
            is.close();
            fos.close();
            setSuccess(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            setSuccess(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setExecuted(true);
    }
}
