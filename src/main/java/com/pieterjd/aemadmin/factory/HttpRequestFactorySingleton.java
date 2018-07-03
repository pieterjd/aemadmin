package com.pieterjd.aemadmin.factory;


/**
 * Entry point for obtaining a HttpRequestFactory based on the platform
 */
public class HttpRequestFactorySingleton {
    private static AEMRequestFactory aemRequestFactory;
    public static HttpRequestFactory getHttpRequestFactoryInstance(Platform platform){
        switch(platform){
            case AEM:
                return getAEMHttpRequestFactory();
            case HIPPO:
                break;
            case SLING:
                break;
        }
        return getAEMHttpRequestFactory();
    }

    private static HttpRequestFactory getAEMHttpRequestFactory(){
        if(aemRequestFactory == null){
            aemRequestFactory = new AEMRequestFactory();
        }
        return aemRequestFactory;
    }
}
