package com.pieterjd.aemadmin.command.packmgr;

import com.github.tsohr.JSONObject;
import com.github.tsohr.XML;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by pdrouill on 29/08/2017.
 */
public class UploadPackageCommand extends PackageMgrCommand {
    private String fileName;



    private boolean install;
    private boolean force;

    /**
     * Uploads a file to the package manager
     * @param fileName The filename to be uploaded. Important: for the name, group, ... to be set correctly, only
     *                 files that are the result of a content package export are properly uploaded
     * @param install install the package
     * @param force force upload the package (and thus overwriting any existing package with the same name)
     */
    public UploadPackageCommand(String fileName,boolean install,boolean force) {
        super("");
        setFileName(fileName);
        setInstall(install);
        setForce(force);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public boolean isInstall() {
        return install;
    }

    public void setInstall(boolean install) {
        this.install = install;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addTextBody("force", Boolean.toString(isForce()))
                .addTextBody("install", Boolean.toString(isInstall()))
                .addBinaryBody("file",new File(getFileName()), ContentType.APPLICATION_OCTET_STREAM,"filename")
                .build();

        HttpUriRequest result =  getAuthenticatedPostRequestBuilder("/crx/packmgr/service.jsp")
                    .setEntity(entity)
                    .build();

        return result;
    }
    @Override
    public JSONObject getHttpResponseAsJSON() throws IOException {
        return XML.toJSONObject(getHttpResponseAsString());
    }
}
