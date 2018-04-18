package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONObject;
import org.junit.Assert;
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
        HttpRequestCommand c = new StatusBundlesCommand();
        c.execute();
        try {
            JSONObject json = c.getHttpResponseAsJSON();
            assertNotNull(json);
            System.out.println(json);
            assertNotNull(json.getString("status"));
            assertNotNull(json.getJSONArray("s"));
            assertNotNull(json.getJSONArray("data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}