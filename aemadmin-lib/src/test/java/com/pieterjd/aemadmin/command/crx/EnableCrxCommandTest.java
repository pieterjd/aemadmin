package com.pieterjd.aemadmin.command.crx;

import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnableCrxCommandTest {
    @Test
    public void testEnableCrx(){
        EnableCrxCommand ecc = new EnableCrxCommand();
        ecc.execute();
        Assert.assertEquals(true,ecc.isSuccess());
        GetNodeCommand gnc = new GetNodeCommand("/apps/system/config/org.apache.sling.jcr.davex.impl.servlets.SlingDavExServlet");
        gnc.execute();
        Assert.assertEquals(true,gnc.isSuccess());
    }

}