package com.pieterjd.aemadmin.command.bundles;

import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.net.URISyntaxException;

@Data
public class InstallBundleCommand extends AbstractBundleCommand {
    private String fileName;
    private boolean startBundle;

    public InstallBundleCommand(String fileName) {
        this(fileName, true);
    }

    public InstallBundleCommand(String fileName, boolean startBundle) {
        super(-1);
        setFileName(fileName);
        setStartBundle(startBundle);
    }

    @Override
    protected String getAction() {
        return "install";
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (isStartBundle()) {
            builder.addTextBody("bundlestart", Boolean.toString(true));
        }
        HttpEntity entity = builder.addTextBody("action", getAction())
                .addBinaryBody("bundlefile", new File(getFileName()), ContentType.APPLICATION_OCTET_STREAM, "filename")
                .build();

        HttpUriRequest result = getAuthenticatedPostRequestBuilder("/system/console/bundles")
                .setEntity(entity)
                .build();
        return result;
    }
}
