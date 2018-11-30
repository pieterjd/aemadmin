package com.pieterjd.aemadmin.shell.utils;

import com.github.tsohr.JSONObject;

import java.util.List;

public class BundleJsonTableModel extends JsonTableModel{
    public BundleJsonTableModel(List<JSONObject> jsonObjects) {
        super(jsonObjects,"id","name","version","state");
    }
}
