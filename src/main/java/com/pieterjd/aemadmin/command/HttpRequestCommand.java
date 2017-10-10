package com.pieterjd.aemadmin.command;

import com.pieterjd.aemadmin.config.ConfigBuilder;
import com.pieterjd.aemadmin.config.LocalAuthorConfigBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.github.tsohr.JSONObject;
import sun.security.krb5.Config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by pdrouill on 21/06/2017.
 */
public abstract class HttpRequestCommand extends AbstractCommand {

    private CloseableHttpClient httpClient;
    private HttpResponse httpResponse;
    private ConfigBuilder configBuilder;

    public HttpRequestCommand() {

        this(new LocalAuthorConfigBuilder());
    }
    public HttpRequestCommand(ConfigBuilder configBuilder){
        setConfigBuilder(configBuilder);
        setHttpClient(HttpClients.createDefault());
    }

    private void setConfigBuilder(ConfigBuilder configBuilder){
        this.configBuilder = configBuilder;
    }

    public Properties getProperties() {
        return configBuilder.build();
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



    public int getPort() {
        return Integer.parseInt(getProperties().getProperty("port"));
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

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public abstract HttpUriRequest getRequest() throws URISyntaxException;

    //helper methods

    /**
     * Returns a Request builder already containing a post request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException
     */
    protected RequestBuilder getAuthenticatedPostRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.post()
                .setUri(new URI(getBaseUrl() + ":" + getPort() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }

    /**
     * Returns a Request builder already containing a get request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException
     */
    protected RequestBuilder getAuthenticatedGetRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.get()
                .setUri(new URI(getBaseUrl() + ":" + getPort() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }

    /**
     * Returns a Request builder already containing a put request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException
     */
    protected RequestBuilder getAuthenticatedPutRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.put()
                .setUri(new URI(getBaseUrl() + ":" + getPort() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }

    /**
     * Returns a Request builder already containing a delete request and authentication header
     *
     * @param urlSuffix this will be appended to the baseUrl. Make sure it starts with "/"
     * @return RequestBuilder
     * @throws URISyntaxException
     */
    protected RequestBuilder getAuthenticatedDeleteRequestBuilder(String urlSuffix) throws URISyntaxException {
        return RequestBuilder.delete()
                .setUri(new URI(getBaseUrl() + ":" + getPort() + urlSuffix))
                .addHeader("Authorization", "Basic " + Base64.encodeBase64String((getUserName() + ":" + getPassword()
                ).getBytes()));

    }


    @Override
    public void execute() {
        try {

            setHttpResponse(getHttpClient().execute(getRequest()));
            int statusCode = getHttpResponse().getStatusLine().getStatusCode();
            setSuccess(200<=statusCode && statusCode<300);

        } catch (Exception e) {
            setSuccess(false);
        } finally {
            setExecuted(true);
        }
    }

    /**
     * Returns the response as a String. The content depends on the actual command:
     * some responses are JSON, other are plain HTML, others are even something else.
     */
    public String getHttpResponseAsString() throws IOException {
        String result = null;
        try {
            result = EntityUtils.toString(getHttpResponse().getEntity());
        }
        catch(Exception ioe){}
        return result;
    }

    /**
     * @return The HTTP response as a JSONObject
     * @throws IOException
     */
    public JSONObject getHttpResponseAsJSON() throws IOException {
        JSONObject result = null;
        result = new JSONObject(getHttpResponseAsString());
        return result;
    }

    /**
     * Writes the response to a file.
     * @param file
     * @throws FileNotFoundException
     */
    public void writeHttpResponseToFile(String file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        try {
            pw.print(getHttpResponseAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.close();
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        ToStringBuilder result = super.getToStringBuilder();
        if(getHttpResponse() != null){
            result = result.append("httpResponseCode",getHttpResponse().getStatusLine().getStatusCode());
        }
        return result;
    }


}
