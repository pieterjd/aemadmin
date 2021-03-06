package com.pieterjd.aemadmin;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONException;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.HttpRequestCommand;
import com.pieterjd.aemadmin.command.LoginCommand;
import com.pieterjd.aemadmin.command.StatusBundlesCommand;
import com.pieterjd.aemadmin.command.aem.security.SearchPrincipalCommand;
import com.pieterjd.aemadmin.command.crx.node.GetNodeCommand;
import com.pieterjd.aemadmin.command.crx.node.ReorderNodeAfterCommand;
import com.pieterjd.aemadmin.command.crx.property.GetPropertyCommand;
import com.pieterjd.aemadmin.command.permissions.AddPermissionCommand;
import com.pieterjd.aemadmin.command.permissions.DeletePermissionCommand;
import com.pieterjd.aemadmin.command.permissions.GetBoundPermissionCommand;
import com.pieterjd.aemadmin.command.permissions.JCRPermission;
import com.pieterjd.aemadmin.config.ConfigBuilder;
import com.pieterjd.aemadmin.config.LocalAuthorConfigBuilder;
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

    @Test
    public void testLoginCommand() {
        LoginCommand c = new LoginCommand();
        c.execute();
        Assert.assertEquals(c.isSuccess(), true);
    }

    @Test
    public void testStatusCommand() {
        HttpRequestCommand c = new StatusBundlesCommand();
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
        c.execute();
        Assert.assertTrue(c.isSuccess());
        c = new GetNodeCommand("/contentbla");
        c.execute();
        Assert.assertFalse(c.isSuccess());

    }

    @Test
    public void testGetPropertyCommand() {
        GetPropertyCommand gpc = new GetPropertyCommand("/etc/clientlibs/rep:policy/allow", "rep:privileges");
        gpc.execute();
        Assert.assertTrue(gpc.isMultiValue());
        gpc = new GetPropertyCommand("/etc/clientlibs/rep:policy/allow", "jcr:primaryType");
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
        c.execute();
        System.out.println("Check in crx if order has been updated");
    }

    @Test
    public void testSearchPrincipalCommand(){
        SearchPrincipalCommand spc = new SearchPrincipalCommand("everyone");
        spc.execute();
        try {
            JSONObject json = spc.getHttpResponseAsJSON();
            JSONArray authorizables = json.getJSONArray("authorizables");
            Assert.assertEquals(1,authorizables.length());
            JSONObject everyone = authorizables.getJSONObject(0);
            Assert.assertEquals("everyone",everyone.getString("id"));
            Assert.assertEquals("group",everyone.getString("type"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBoundPermissionTest(){
        GetBoundPermissionCommand c =new GetBoundPermissionCommand("/apps");
        c.execute();
        try {
            JSONObject response = c.getHttpResponseAsJSON();
            System.out.println(response.toString(1));
            Assert.assertNotNull(response.getJSONObject("sling-jcr-install"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEffectivePermissionTest(){
        GetBoundPermissionCommand c =new GetBoundPermissionCommand("/apps");
        c.execute();
        try {
            JSONObject response = c.getHttpResponseAsJSON();
            Assert.assertNotNull(response.getJSONObject("sling-jcr-install"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this test expect a JSONException when all runs correctly
    @Test(expected = JSONException.class)
    public void getAddAndRemovePermissionTest(){
        AddPermissionCommand a =new AddPermissionCommand("/apps","everyone", JCRPermission.ALL);
        a.execute();
        GetBoundPermissionCommand g =new GetBoundPermissionCommand("/apps");
        g.execute();
        try {
            JSONObject response = g.getHttpResponseAsJSON();
            Assert.assertNotNull(response.getJSONObject("everyone"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeletePermissionCommand d = new DeletePermissionCommand("/apps","everyone");
        d.execute();
        g =new GetBoundPermissionCommand("/apps");
        g.execute();
        try {
            JSONObject response = g.getHttpResponseAsJSON();
            //getJSONObject call should throw an exception because this node is deleted
            Assert.assertNull(response.getJSONObject("everyone"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
