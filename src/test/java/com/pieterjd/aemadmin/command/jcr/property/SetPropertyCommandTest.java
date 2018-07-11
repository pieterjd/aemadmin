package com.pieterjd.aemadmin.command.jcr.property;

import org.junit.Test;

import java.io.IOException;

public class SetPropertyCommandTest {

    public static final String PATH = "/tmp";
    public static final String TYPED_PROPERTY_NAME = "dummyboolean";
    public static final String UNTYPED_PROPERTY_NAME = "dummy";



    @Test
    public void testProperty(){
        SetPropertyCommand spc = new SetPropertyCommand(PATH, UNTYPED_PROPERTY_NAME,"Some value 1234");
        spc.execute();

        GetPropertyCommand gpc = new GetPropertyCommand(PATH,UNTYPED_PROPERTY_NAME);
        gpc.execute();
        try {
            gpc.getHttpResponseAsJSON().getString(UNTYPED_PROPERTY_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExplictlyTypedProperty(){
        SetPropertyCommand spc = new SetPropertyCommand(PATH, TYPED_PROPERTY_NAME,"True","Boolean");
        spc.execute();

        GetPropertyCommand gpc = new GetPropertyCommand(PATH,TYPED_PROPERTY_NAME);
        gpc.execute();
        try {
            gpc.getHttpResponseAsJSON().getBoolean(TYPED_PROPERTY_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}