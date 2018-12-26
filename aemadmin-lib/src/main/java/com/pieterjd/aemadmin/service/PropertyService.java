package com.pieterjd.aemadmin.service;

import java.io.IOException;

public interface PropertyService {
    public String getProperty(String path,String propertyName) throws IOException;

    public String deleteProperty(String path,String propertyName) throws IOException;

    public String newProperty(String path,String propertyName,String propertyValue) throws IOException;
}
