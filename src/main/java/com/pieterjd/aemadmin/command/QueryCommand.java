package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pdrouill on 22/06/2017.
 */
public class QueryCommand extends HttpRequestCommand {
    private Map<String,String> conditions;



    private JSONObject queryResult;

    public QueryCommand() {
        super();
        this.conditions = new HashMap<>();
        queryResult = null;
    }



    public int size() {
        return conditions.size();
    }

    public String get(Object key) {
        return conditions.get(key);
    }

    public String put(String key, String value) {
        return conditions.put(key, value);
    }
    public QueryCommand addCondition(String key,String value){
         put(key,value);
         return this;
    }

    public String remove(Object key) {
        return conditions.remove(key);
    }

    public Map<String, String> getConditions() {
        return conditions;
    }
    private String getQueryString(){
        StringBuilder sb = new StringBuilder();
        for(String key:conditions.keySet()){
            if(sb.length()!=0){
                sb.append("&");
            }
            sb.append(key).append("=").append(conditions.get(key));

        }
        return sb.toString();
    }
    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder("/bin/querybuilder.json?"+getQueryString()).build();
       }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return new ToStringBuilder(this)
                .append("conditions", getQueryString());
    }

    /**
     * Returns the response of the query as a {@link JSONObject}
     * @return JSON when query was successful, null when something went wrong
     */
    public JSONObject getQueryResult(){
        if(queryResult == null){
            String response = null;
            try {
                response = getHttpResponseAsString();
                if(response != null){
                    queryResult = new JSONObject(response);
                }
            } catch (IOException e) {
                return null;
            }

        }
        return queryResult;
    }

    //som shortcut methods
    public QueryCommand setHitLimit(int limit){
        addCondition("p.limit",Integer.toString(limit));
        return this;
    }

    public QueryCommand propertyExists(String prefix, String propertyName){
        addCondition(prefix+"property",propertyName);
        addCondition(prefix+"property.operation","exists");
        addCondition(prefix+"property.value","true");
        return this;
    }

    public QueryCommand fullHit(){
        return addCondition("p.hits","full");
    }

    public int getResults(){
        return getQueryResult().getInt("results");
    }

    public int getTotal(){
        return getQueryResult().getInt("total");
    }

    public boolean hasMore(){
        return getQueryResult().getBoolean("more");
    }

}
