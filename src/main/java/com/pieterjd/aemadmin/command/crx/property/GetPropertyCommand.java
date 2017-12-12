package com.pieterjd.aemadmin.command.crx.property;


import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONException;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;

public class GetPropertyCommand extends PropertyCommand {
    public GetPropertyCommand(String path, String propertyName) {
        super(path, propertyName, null);
    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder(getPath()+".json").build();
    }

    /**
     * Checks if the property is multi-valued or not
     * @return true if property is multi-valued, false otherwise
     */
    public boolean isMultiValue(){
        boolean result = true;
        try {
            JSONArray array =  getHttpResponseAsJSON().getJSONArray(getPropertyName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(JSONException je){
            //JSONException is thrown when retrieving a non-array property as an array
            result = false;
        }
        return result;
    }
    public String getPropertyValue(){
        try {
            return getHttpResponseAsJSON().getString(getPropertyName());
        } catch (Exception e) {

        }
        finally {
            return null;
        }
    }

    public JSONArray getMultiPropertyValue(){
        try {
            return getHttpResponseAsJSON().getJSONArray(getPropertyName());
        } catch (IOException e) {

        }
        return null;
    }
}
