package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Created by pdrouill on 22/06/2017.
 */
public class DeleteNodeCommand extends CrxCommand {
    public DeleteNodeCommand(String path) {
        super(path);
    }

    @Override
    public void execute(){
        try{
            super.execute();
        }
        catch(Exception e){

        }
        finally{
            int statusCode = getHttpResponse().getStatusLine().getStatusCode();
            setSuccess(200 <= statusCode && statusCode < 300);
        }
    }

    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedDeleteRequestBuilder("/crx/server/crx.default/jcr:root"+getPath()).build();
    }
}
