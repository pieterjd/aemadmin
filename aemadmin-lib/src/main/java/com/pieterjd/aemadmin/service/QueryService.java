package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONObject;

import java.util.List;

public interface QueryService {
    List<JSONObject> query(String criteria);
}