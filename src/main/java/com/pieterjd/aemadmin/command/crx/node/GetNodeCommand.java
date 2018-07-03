package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * Returns a node up to a certain depth in JSON format.
 */
public class GetNodeCommand extends CrxCommand {
    private Integer depth;
    public GetNodeCommand(String path){
        this(path,1);
    }
    public GetNodeCommand(String path,Integer depth) {
        super(path);
        setDepth(depth);
    }



    public Integer getDepth() {
        return depth;
    }

    private void setDepth(Integer depth) {
        this.depth = depth;
    }

    public HttpUriRequest getRequest() throws URISyntaxException {
        return getHttpRequestFactory().getGetNodeHttpRequest(getPath(),getDepth());
    }
}
