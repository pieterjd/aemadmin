package com.pieterjd.aemadmin.command.crx.property;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeletePropertyCommandTest {
    String path, property, value;
    @Before
    public void setUp() throws Exception {
        path = "/tmp";
        property = "testProperty";
        value ="bla";
    }

    @Test
    public void getRequest() throws Exception {
        SetPropertyCommand spc = new SetPropertyCommand(path,property,value);
        spc.execute();
        GetPropertyCommand gpc = new GetPropertyCommand(path,property);
        gpc.execute();
        assertEquals(gpc.getPropertyValue(),value);
        DeletePropertyCommand dpc = new DeletePropertyCommand(path,property);
        dpc.execute();
        gpc.execute();
        assertNull(gpc.getPropertyValue());
    }

}