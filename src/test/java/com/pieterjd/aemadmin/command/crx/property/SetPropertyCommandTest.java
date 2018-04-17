package com.pieterjd.aemadmin.command.crx.property;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetPropertyCommandTest {
    @Test
    public void getRequest() throws Exception {
        GetPropertyCommand gpc = new GetPropertyCommand("/tmp", "jcr:created");
        gpc.execute();
        assertFalse(gpc.isMultiValue());
    }

    @Test
    public void getRequestMultiValue() throws Exception {
        GetPropertyCommand gpc = new GetPropertyCommand("/etc/clientlibs/rep:policy/allow", "rep:privileges");

        gpc.execute();
        Assert.assertTrue(gpc.isMultiValue());
        gpc = new GetPropertyCommand("/etc/clientlibs/rep:policy/allow", "jcr:primaryType");

        gpc.execute();
        Assert.assertFalse(gpc.isMultiValue());
    }

}