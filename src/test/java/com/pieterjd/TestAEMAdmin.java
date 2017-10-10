package com.pieterjd;

import com.pieterjd.aemadmin.command.AbstractCommand;
import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.LoginCommand;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;
import com.pieterjd.aemadmin.command.crx.node.CopyNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by pdrouill on 23/08/2017.
 */


public class TestAEMAdmin {
    @Test
    public void testLoginCommand(){
        LoginCommand c = new LoginCommand();
        c.execute();
        Assert.assertEquals(c.isSuccess(),true);
    }
    @Test
    public void testStatusCommand(){
        HttpRequestCommand c = new StatusBundlesCommand();
        c.execute();
        try {
            Assert.assertNotNull(c.getHttpResponseAsJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetNodeCommand(){
        HttpRequestCommand c = new GetNodeCommand("/content");
        c.execute();
        Assert.assertTrue(c.isSuccess());
        c = new GetNodeCommand("/contentbla");
        c.execute();
        Assert.assertFalse(c.isSuccess());

    }
    @Test
    public void testCopyAndDeleteNodeCommand(){
        HttpRequestCommand c = new CopyNodeCommand("/apps/settings","/settingscopy");
        c.execute();

    }

}
