package com.pieterjd.aemadmin.command.packmgr;

import com.github.tsohr.JSONObject;
import com.github.tsohr.XML;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by pdrouill on 29/08/2017.
 */
public class ListPackagesCommand extends PackageMgrCommand {
    public ListPackagesCommand() {
        super("");
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder("/crx/packmgr/service.jsp?cmd=ls").build();
    }

    @Override
    public JSONObject getHttpResponseAsJSON() throws IOException {
        return XML.toJSONObject(getHttpResponseAsString());
    }
}
