package com.pieterjd.aemadmin.command.aem;

import com.pieterjd.aemadmin.command.jcr.node.GetNodeCommand;
import com.pieterjd.aemadmin.command.jcr.property.GetPropertyCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatePageCommandTest {
    private String path,name,title,resourceType,template;

    @Before
    public void setUp() throws Exception {
        path = "/content/we-retail/ca/en";
        name = "test";
        title = "test page";
        resourceType = "weretail/components/structure/page";
        template = "/conf/we-retail/settings/wcm/templates/hero-page";
    }

    @Test
    public void execute() throws Exception {
        CreatePageCommand cpc = new CreatePageCommand(path,name,title,resourceType,template);
        cpc.execute();
        GetNodeCommand gnc = new GetNodeCommand(path);
        gnc.execute();
        assertTrue(gnc.isSuccess());
    }

    @Test
    public void executeDeleteFirst() throws Exception {
        execute();
        GetPropertyCommand gpc = new GetPropertyCommand(path+"/"+name,"jcr:created");
        gpc.execute();
        String oldValue = gpc.getPropertyValue();
        CreatePageCommand cpc = new CreatePageCommand(path,name,title,resourceType,template,true);
        cpc.execute();

        gpc = new GetPropertyCommand(path+"/"+name,"jcr:created");
        gpc.execute();
        String newValue = gpc.getPropertyValue();
        assertNotEquals(oldValue,newValue);
    }

}