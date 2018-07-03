package com.pieterjd.aemadmin.command.aem.replication;

import com.pieterjd.aemadmin.command.jcr.node.DeleteNodeCommand;
import com.pieterjd.aemadmin.command.jcr.property.GetPropertyCommand;
import com.pieterjd.aemadmin.config.LocalPublishConfigBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActivatePageCommandTest {
    private String path;
    @Before
    public void setUp() throws Exception {
        path = "/content/we-retail";
    }

    @Test
    public void activate(){
        //remove page on publish
        DeleteNodeCommand dnc = new DeleteNodeCommand(path);
        dnc.setConfigBuilder(new LocalPublishConfigBuilder());
        dnc.execute();
        ActivatePageCommand apc = new ActivatePageCommand(path);
        apc.execute();
        assertTrue(apc.isSuccess());
        //check on local publish
        GetPropertyCommand gpc = new GetPropertyCommand(path,"jcr:primaryType");
        gpc.setConfigBuilder(new LocalPublishConfigBuilder());
        gpc.execute();
        assertTrue(gpc.isSuccess());
    }

}