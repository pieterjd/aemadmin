package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PackageService {
    List<JSONObject> listPackages() throws IOException;

    String installPackage(File packageName) throws IOException;

    String buildPackage(String packageName) throws IOException;

    String downloadPackage(String packageName, String fileName) throws IOException;
}
