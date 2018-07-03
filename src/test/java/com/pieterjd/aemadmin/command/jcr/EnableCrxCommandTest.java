package com.pieterjd.aemadmin.command.jcr;

import com.pieterjd.aemadmin.command.aem.EnableCrxCommand;
import com.pieterjd.aemadmin.command.jcr.node.GetNodeCommand;
import org.junit.Assert;
import org.junit.Test;

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