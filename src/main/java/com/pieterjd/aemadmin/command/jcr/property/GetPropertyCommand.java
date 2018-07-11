package com.pieterjd.aemadmin.command.jcr.property;


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
        return getHttpRequestFactory().getGetPropertyHttpRequest(getPath(),getPropertyName());
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

    /**
     * Returns the value of the property. If the property does not exist, <code>null</code> is returned
     * @return the value of the property or <code>null</code>
     */
    public String getPropertyValue(){
        String result = null;
        try {
            result = getHttpResponseAsJSON().getString(getPropertyName());
        } catch (Exception e) {

        }
        return result;
    }

    public JSONArray getMultiPropertyValue(){
        try {
            return getHttpResponseAsJSON().getJSONArray(getPropertyName());
        } catch (IOException e) {

        }
        return null;
    }
}
