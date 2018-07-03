package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.HttpRequestCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetNodeCommandTest {
    @Test
    public void getRequest() throws Exception {
        HttpRequestCommand c = new GetNodeCommand("/content");
        c.execute();
        Assert.assertTrue(c.isSuccess());
        c = new GetNodeCommand("/contentbla");
        c.execute();
        Assert.assertFalse(c.isSuccess());
    }

}