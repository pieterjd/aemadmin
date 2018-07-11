package com.pieterjd.aemadmin.command.jcr.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteNodeCommandTest {
    private String path,primaryType;
    @Before
    public void setUp() throws Exception {
        path = "/tmp/testing";
        primaryType = "sling:Folder";

    }
    @Test
    public void getRequest() throws Exception {
        CreateNodeCommand cnc = new CreateNodeCommand(path,primaryType);
        cnc.execute();
        assertTrue(cnc.isSuccess());
        DeleteNodeCommand dnc = new DeleteNodeCommand(path);
        dnc.execute();
        assertTrue(dnc.isSuccess());
        GetNodeCommand gnc = new GetNodeCommand(path);
        gnc.execute();
        assertFalse(path+ " should not exist after running this test",gnc.isSuccess());
    }

}