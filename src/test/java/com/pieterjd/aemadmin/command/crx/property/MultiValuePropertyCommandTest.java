package com.pieterjd.aemadmin.command.crx.property;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MultiValuePropertyCommandTest {

    @Test
    public void testAddToMultiValue() {
        DeletePropertyCommand dpc = new DeletePropertyCommand("/tmp","multitest");
        dpc.execute();
        assertTrue(dpc.isSuccess());
        AddToMultiValuePropertyCommand amvpc1 = new AddToMultiValuePropertyCommand("/tmp","multitest","multi1");
        amvpc1.execute();
        assertTrue(amvpc1.isSuccess());
        AddToMultiValuePropertyCommand amvpc2 = new AddToMultiValuePropertyCommand("/tmp","multitest","multi2");
        amvpc2.execute();
        assertTrue(amvpc2.isSuccess());
        GetPropertyCommand gpc = new GetPropertyCommand("/tmp","multitest");
        gpc.execute();
        assertTrue(gpc.isMultiValue());
        assertEquals(gpc.getMultiPropertyValue().length(),2);
    }

    @Test
    public void testRemoveFromMultiValue(){
        testAddToMultiValue();
        RemoveFromMultiValuePropertyCommand rfmvp = new RemoveFromMultiValuePropertyCommand("/tmp","multitest","multi1");
        rfmvp.execute();
        GetPropertyCommand gpc = new GetPropertyCommand("/tmp","multitest");
        gpc.execute();
        assertTrue(gpc.isMultiValue());
        assertEquals(gpc.getMultiPropertyValue().length(),1);

    }
}