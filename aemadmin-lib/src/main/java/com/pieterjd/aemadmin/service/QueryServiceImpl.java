package com.pieterjd.aemadmin.service;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.command.QueryCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class QueryServiceImpl implements QueryService {
    @Override
    public List<JSONObject> query(String criteria) {
        QueryCommand c = new QueryCommand();
        Stream.of(criteria.split(","))
                .map(crit -> crit.split("="))
                .forEach(tuple ->c.addCondition(tuple[0],tuple[1]));
        c.execute();
        JSONArray array = c.getQueryResult().getJSONArray("hits");
        List<JSONObject> hits = new ArrayList<>();

        for(int i = 0;i<array.length();i++){
            hits.add(array.getJSONObject(i));
        }
        return hits;
    }
}
