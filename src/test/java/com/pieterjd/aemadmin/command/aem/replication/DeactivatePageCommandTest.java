package com.pieterjd.aemadmin.command.aem.replication;

import com.pieterjd.aemadmin.command.jcr.property.GetPropertyCommand;
import com.pieterjd.aemadmin.config.LocalPublishConfigBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeactivatePageCommandTest {
    private String path;
    @Before
    public void setUp() throws Exception {
        path = "/content/we-retail";
    }

    @Test
    public void deactivate(){
        //check page is on publish
        //check on local publish
        GetPropertyCommand gpc = new GetPropertyCommand(path,"jcr:primaryType");
        gpc.setConfigBuilder(new LocalPublishConfigBuilder());
        gpc.execute();
        assertTrue(gpc.isSuccess());

        DeactivatePageCommand dpc = new DeactivatePageCommand(path);
        dpc.execute();
        //recheck if it's gone
        gpc.execute();
        assertFalse(gpc.isSuccess());
    }

}