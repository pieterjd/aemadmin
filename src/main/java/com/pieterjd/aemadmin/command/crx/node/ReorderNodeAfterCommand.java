package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdrouill on 11/07/2017.
 * Reorders by putting one node explicitly after an other.
 *
 * The node at path will come after siblingNodename
 *
 */
public class ReorderNodeAfterCommand extends ReorderNodeCommand{


    /**
     * Path of the crx node. this path should start with a "/"
     *
     * The node at path will come after siblingnode.
     *
     * @param path            the path to the node you want to interact with
     * @param siblingNodename the node you want to reorder relative to path
     */
    public ReorderNodeAfterCommand(String path, String siblingNodename) {
        super(path, siblingNodename);
    }

    @Override
    protected String getOrderValue() {
        return "after "+getSiblingNodename();
    }

    /**
     * Deprecated. Is provided for compactibility with version before refactoring.
     * @return
     */
    @Deprecated
    public String getPutAfterNode() {
        return getSiblingNodename();
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("HumanReadable",getPath()+" is now after "+getSiblingNodename());
    }
}
