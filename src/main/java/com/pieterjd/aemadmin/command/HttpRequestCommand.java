package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONObject;
import com.pieterjd.aemadmin.config.ConfigBuilder;
import com.pieterjd.aemadmin.config.ConfigFactory;
import com.pieterjd.aemadmin.config.LocalAuthorConfigBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by pdrouill on 21/06/2017.
 * Command that executes a httprequest. Contains a lot of helper function, eg create authenticated requests.
 */
public abstract class HttpRequestCommand extends AbstractCommand {

    private CloseableHttpClient httpClient;
    private HttpResponse httpResponse;
    private String httpResponseAsString;
    private Properties properties;



    /**
     * Creates HttpRequestCommand using local author
     */
    public HttpRequestCommand() {

       setProperties(ConfigFactory.getConfig());
        setHttpClient(HttpClients.createDefault());
    }

    /**
     * Creates HttpRequestCommand using the given config builder
     *
     * @param configBuilder ConfigBuilder used to setup all http requests
     */
    public HttpRequestCommand(ConfigBuilder configBuilder) {
        setConfigBuilder(configBuilder);
        setHttpClient(HttpClients.createDefault());
    }

    public void setConfigBuilder(ConfigBuilder configBuilder) {
        setProperties(configBuilder.build());
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getUserName() {
        return getProperties().getProperty("userName");
    }


    public String getPassword() {
        return getProperties().getProperty("password");
    }


    public String getBaseUrl() {
        return getProperties().getProperty("baseUrl");
    }


    public Integer getPort() {

        Integer result = null;
        try{
            result = Integer.parseInt(getProperties().getProperty("port"));
        }catch (NumberFormatException nfe){

        }
        return result;
    }


    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) throws IOException {
        this.httpResponse = httpResponse;
        this.httpResponseAsString = EntityUtils.toString(httpResponse.getEntity());
    }

    /**
     * @return the request that the command will execute
     * @throws URISyntaxException When something is wrong with syntax
     * of the uri you use to create the request
     */
    public abstract HttpUriRequest getRequest() throws URISyntaxException;

    //helper methods

    /**
     * Returns the host string part. Format is either the baseUrl or baseUrl:port if portnumber is defined.
     * @return Properly formatted host
     */
    protected String buildUri(){
        String uri = getBaseUrl();
        if(getPort() != null){
            uri +=":"+getPort().toString();
        }
        return uri;
    }

    /**
     * Returns a Request builder already containing a post request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException When something is wrong with syntax
     * of the uri you use to create the request
     */
    protected RequestBuilder getAuthenticatedPostRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.post()
                .setUri(new URI(buildUri() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }

    /**
     * Returns a Request builder already containing a get request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException When something is wrong with syntax
     * of the uri you use to create the request
     */
    protected RequestBuilder getAuthenticatedGetRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.get()
                .setUri(new URI(buildUri() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }

    /**
     * Returns a Request builder already containing a put request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException When something is wrong with syntax
     * of the uri you use to create the request
     */
    protected RequestBuilder getAuthenticatedPutRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.put()
                .setUri(new URI(buildUri() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }

    /**
     * Returns a Request builder already containing a delete request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException When something is wrong with syntax
     * of the uri you use to create the request
     */
    protected RequestBuilder getAuthenticatedDeleteRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.delete()
                .setUri(new URI(buildUri() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }


    /**
     * Default implementation. It executes the result of {@link #getRequest()}
     */
    @Override
    public void execute() {
        try {

            setHttpResponse(getHttpClient().execute(getRequest()));
            int statusCode = getHttpResponse().getStatusLine().getStatusCode();
            setSuccess(200 <= statusCode && statusCode < 300);

        } catch (Exception e) {
            setSuccess(false);
        } finally {
            setExecuted(true);
        }
    }

    /**
     * Returns the response as a String. The content depends on the actual command:
     * some responses are JSON, other are plain HTML, others are even something else.
     * @return the response in String format
     * @throws IOException Something goes wrong with getting the response
     */
    public String getHttpResponseAsString() throws IOException {
        return httpResponseAsString;
    }

    /**
     * @return The HTTP response as a JSONObject
     * @throws IOException when something goes wrong getting the response
     */
    public JSONObject getHttpResponseAsJSON() throws IOException {
        return new JSONObject(getHttpResponseAsString());
    }

    /**
     * Writes the response to a file.
     *
     * @param file the path of the file you want to write to
     * @throws FileNotFoundException When you provide an invalid filepath
     */
    public void writeHttpResponseToFile(String file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        try {
            pw.print(getHttpResponseAsString());
        } catch (IOException e) {

        }
        finally {
            pw.close();
        }

    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        ToStringBuilder result = super.getToStringBuilder();
        result.append("baseUrl",getBaseUrl());
        if (getHttpResponse() != null) {
            result = result.append("httpResponseCode", getHttpResponse().getStatusLine().getStatusCode());
        }
        return result;
    }

}
