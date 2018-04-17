package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateNodeCommandTest {
    private String path,primaryType;
    @Before
    public void setUp() throws Exception {
        path = "/tmp/testing";
        primaryType = "sling:Folder";

    }

    @Test
    public void getRequest() throws Exception {
        GetNodeCommand gnc = new GetNodeCommand(path);
        gnc.execute();
        assertFalse(path+ " should not exist before running this test",gnc.isSuccess());
        CreateNodeCommand cnc = new CreateNodeCommand(path,primaryType);
        cnc.execute();
        gnc.execute();
        assertTrue(gnc.isSuccess());
        GetPropertyCommand gpc = new GetPropertyCommand(path,"jcr:primaryType");
        gpc.execute();
        assertEquals(gpc.getPropertyValue(),primaryType);

    }

}