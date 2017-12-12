package com.pieterjd.aemadmin;

import com.pieterjd.aemadmin.command.AbstractCommand;
import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.LoginCommand;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;
import com.pieterjd.aemadmin.command.crx.node.CopyNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.ReorderNodeAfterCommand;
import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import com.pieterjd.aemadmin.config.ConfigBuilder;
import com.pieterjd.aemadmin.config.LocalPublishConfigBuilder;
import com.pieterjd.aemadmin.config.PropertiesConfigBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by pdrouill on 23/08/2017.
 * <p>
 * These tests should be run on a quickstart AEM install.
 */


public class TestAEMAdmin {
    private ConfigBuilder configBuilder;

    @Before
    public void setUp(){
        configBuilder = new LocalPublishConfigBuilder();
    }
    @Test
    public void testLoginCommand() {
        LoginCommand c = new LoginCommand();
        c.setConfigBuilder(configBuilder);
        c.execute();
        Assert.assertEquals(c.isSuccess(), true);
    }

    @Test
    public void testStatusCommand() {
        HttpRequestCommand c = new StatusBundlesCommand();
        c.setConfigBuilder(configBuilder);
        c.execute();
        try {
            Assert.assertNotNull(c.getHttpResponseAsJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetNodeCommand() {
        HttpRequestCommand c = new GetNodeCommand("/content");
        c.setConfigBuilder(configBuilder);
        c.execute();
        Assert.assertTrue(c.isSuccess());
        c = new GetNodeCommand("/contentbla");
        c.setConfigBuilder(configBuilder);
        c.execute();
        Assert.assertFalse(c.isSuccess());

    }

    @Test
    public void testGetPropertyCommand() {
        GetPropertyCommand gpc = new GetPropertyCommand("/etc/clientlibs/rep:policy/allow", "rep:privileges");
        gpc.setConfigBuilder(configBuilder);
        gpc.execute();
        Assert.assertTrue(gpc.isMultiValue());
        gpc = new GetPropertyCommand("/etc/clientlibs/rep:policy/allow", "jcr:primaryType");
        gpc.setConfigBuilder(configBuilder);
        gpc.execute();
        Assert.assertFalse(gpc.isMultiValue());
    }

    @Test
    public void testOrderAfterCommand() {
        /*
        under /content/kpngb-base/jcr:content:
        node image-retail is put after title-retail
         */

        ReorderNodeAfterCommand c = new ReorderNodeAfterCommand("/content/kpngb-base/jcr:content/image-retail",
                "title-retail");
        c.setConfigBuilder(configBuilder);
        c.execute();
        System.out.println("Check in crx if order has been updated");
    }

}
