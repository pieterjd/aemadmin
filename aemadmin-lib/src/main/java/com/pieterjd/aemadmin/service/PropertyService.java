package com.pieterjd.aemadmin.service;

import java.io.IOException;

public interface PropertyService {
    String getProperty(String path,String propertyName) throws IOException;

    String deleteProperty(String path,String propertyName) throws IOException;

    String newProperty(String path,String propertyName,String propertyValue) throws IOException;
}
