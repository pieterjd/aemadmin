package com.pieterjd.aemadmin.command.packmgr;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

        try {
            CloseableHttpResponse response = getHttpClient().execute(getRequest());
            FileOutputStream fos = new FileOutputStream(getFileName());
            response.getEntity().writeTo(fos);
            fos.close();
            setSuccess(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setExecuted(true);
    }
}
