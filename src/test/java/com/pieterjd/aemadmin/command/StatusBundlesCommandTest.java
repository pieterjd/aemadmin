package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.bundles.StatusBundlesCommand;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StatusBundlesCommandTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void execute(){
        StatusBundlesCommand c = new StatusBundlesCommand();
        c.execute();
        try {
            JSONObject json = c.getHttpResponseAsJSON();
            assertNotNull(json);
            System.out.println(json);
            assertNotNull(json.getString("status"));
            assertNotNull(json.getJSONArray("s"));
            assertNotNull(json.getJSONArray("data"));
            assertTrue(c.getTotalBundlesCount()>=0);
            assertTrue(c.getActiveBundlesCount()>=0);
            assertTrue(c.getActiveFragmentsCount()>=0);
            assertTrue(c.getResolvedBundlesCount()>=0);
            assertTrue(c.getTotalBundlesCount()==c.getActiveBundlesCount()+c.getActiveFragmentsCount()+c.getResolvedBundlesCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}