package com.pieterjd.aemadmin.command.jcr.node;

import com.pieterjd.aemadmin.command.jcr.JcrCommand;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Created by pdrouill on 3/07/2017.
 */
public class CreateNodeCommand extends JcrCommand {
    private String primaryType;
    public CreateNodeCommand(String path,String primaryType) {
        super(path);
        setPrimaryType(primaryType);
    }



    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getCreateNodeHttpRequest(getPath(),getPrimaryType());
    }
}
