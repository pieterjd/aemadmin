package com.pieterjd.aemadmin.service;

public interface ConfigService {
    void connect(String baseUrl, String userName, String password, String port);

    void connectLocalAuthor();

    void connectLocalPublish();
}
