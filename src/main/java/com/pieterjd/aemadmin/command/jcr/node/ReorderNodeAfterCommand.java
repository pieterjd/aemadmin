package com.pieterjd.aemadmin.command.jcr.node;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pdrouill on 11/07/2017.
 * Reorders by putting one node explicitly after an other.
 *
 * The node at path will come after siblingNodename
 *
 */
public class ReorderNodeAfterCommand extends ReorderNodeCommand{


    /**
     * Path of the jcr node. this path should start with a "/"
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
     * @return name of the sibling node
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
