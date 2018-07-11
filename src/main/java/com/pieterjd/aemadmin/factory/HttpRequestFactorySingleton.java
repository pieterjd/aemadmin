package com.pieterjd.aemadmin.factory;


/**
 * Entry point for obtaining a HttpRequestFactory based on the platform
 */
public class HttpRequestFactorySingleton {
    private static SlingRequestFactoryImpl slingRequestFactory;
    private static AEMRequestFactoryImpl aemRequestFactory;
    public static HttpRequestFactory getHttpRequestFactoryInstance(Platform platform){
        switch(platform){
            case AEM:
                break;
            case HIPPO:
                break;
            case SLING:
                return getSlingHttpRequestFactory();
        }
        return getSlingHttpRequestFactory();
    }

    private static HttpRequestFactory getSlingHttpRequestFactory(){
        if(slingRequestFactory == null){
            slingRequestFactory = new SlingRequestFactoryImpl();
        }
        return slingRequestFactory;
    }

    private static HttpRequestFactory getAEMHttpRequestFactory(){
        if(aemRequestFactory == null){
            aemRequestFactory = new AEMRequestFactoryImpl();
        }
        return aemRequestFactory;
    }
}
