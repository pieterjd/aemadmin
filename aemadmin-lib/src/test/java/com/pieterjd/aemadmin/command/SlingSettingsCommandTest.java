package com.pieterjd.aemadmin.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlingSettingsCommandTest {
    private SlingSettingsCommand ssc;

    @Before
    public void setUp() throws Exception {
        ssc = new SlingSettingsCommand();
        ssc.execute();
    }

    @Test
    public void getSlingName() throws Exception {
        Assert.assertEquals("name","Instance "+ssc.getSlingID(),ssc.getSlingName());
    }

    @Test
    public void getRunmodes() throws Exception {
        Assert.assertEquals(true,ssc.getRunmodes().contains("author"));
    }

}