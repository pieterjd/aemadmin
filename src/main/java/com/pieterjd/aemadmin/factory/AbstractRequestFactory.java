package com.pieterjd.aemadmin.factory;

import com.pieterjd.aemadmin.config.LocalAuthorConfigBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.RequestBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public abstract class AbstractRequestFactory implements HttpRequestFactory {

    private Properties properties;

    public static final String CMDLINE_PROPERTY_FILE = "propertiesFile";

    /**
     * If the propertiesFile cmd line property is set, read that file; otherwise use the local author instance
     * @return The properties of the node to connect to
     */
    private static Properties readDefaultProperties() {
        Properties result = new Properties();

        if(System.getProperty(CMDLINE_PROPERTY_FILE) != null){
            try (InputStream inputStream = new FileInputStream(System.getProperty("propertiesFile"))) {
                result.load(inputStream);
            } catch (IOException e) {
                System.out.println("Cannot read cmdline provided properties file");
                result = new LocalAuthorConfigBuilder().build();
            }
        }
        else {
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("aemadmin.properties")) {
                result.load(inputStream);
            } catch (IOException e) {
                result = new LocalAuthorConfigBuilder().build();
            }
        }
        return result;
    }
    public AbstractRequestFactory(){
        setProperties(readDefaultProperties());
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
}
