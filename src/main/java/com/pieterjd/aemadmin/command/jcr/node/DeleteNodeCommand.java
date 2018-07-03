package com.pieterjd.aemadmin.command.jcr.node;

import com.pieterjd.aemadmin.command.jcr.JcrCommand;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Created by pdrouill on 22/06/2017.
 */
public class DeleteNodeCommand extends JcrCommand {
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
            setSuccess((HttpStatus.SC_ACCEPTED <= statusCode && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) || statusCode == HttpStatus.SC_NOT_FOUND);
        }
    }

    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getDeleteNodeHttpRequest(getPath());
    }
}
