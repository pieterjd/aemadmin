package com.pieterjd.aemadmin.shell.utils;

import com.github.tsohr.JSONObject;
import org.springframework.shell.table.TableModel;

import java.util.ArrayList;
import java.util.List;

public class JsonTableModel extends TableModel {
    private List<JSONObject> jsonObjects;
    private List<String> properties;
    private boolean showAllProperties;
    private boolean showColumnNamesAtEnd;

    public JsonTableModel(List<JSONObject> jsonObjects){
        this(jsonObjects,new String[0]);
    }

    public JsonTableModel(List<JSONObject> jsonObjects,String...properties) {
        this.jsonObjects = jsonObjects;
       this.properties = new ArrayList<>();
       for(String prop: properties){
           this.properties.add(prop);
       }
       this.showAllProperties = this.properties.size() == 0;
       this.showColumnNamesAtEnd = this.jsonObjects.size()>20;
    }

    @Override
    public int getRowCount() {
        int count = jsonObjects.size()+1;
        if(showColumnNamesAtEnd) count++;
        return count;
    }

    @Override
    public int getColumnCount() {
        int count = jsonObjects.get(0).length();
        if(!showAllProperties){
            count = properties.size();
        }
        return count;
    }

    @Override
    public Object getValue(int row, int column) {
        String key = jsonObjects.get(0).names().getString(column);
        if(!showAllProperties){
            key = properties.get(column);
        }
        if(row == 0 || (showColumnNamesAtEnd && row == getRowCount()-1)){
            return key;
        }

        return jsonObjects.get(row-1).get(key);
    }
}
