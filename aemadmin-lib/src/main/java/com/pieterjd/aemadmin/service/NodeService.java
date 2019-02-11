package com.pieterjd.aemadmin.service;

import java.io.IOException;

public interface NodeService {
    String getNode(String path) throws IOException;

    String deleteNode(String path) throws IOException;

    String newNode(String path, String primaryType) throws IOException;
}
