package com.pieterjd.aemadmin.command;

import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlingSettingsCommand extends HttpRequestCommand {
    //regular expressions to parse array entries
    private Map<String, String> regexs;
    private Map<String, String> parsed;

    private final String SLING_ID = "SLING_ID";
    private final String SLING_NAME = "SLING_NAME";
    private final String SLING_DESCRIPTION = "SLING_DESCRIPTION";
    private final String SLING_HOME = "SLING_HOME";
    private final String SLING_HOME_URL = "SLING_HOME_URL";
    private final String RUNMODES = "RUNMODES";

    public SlingSettingsCommand() {
        super();
        regexs = new HashMap<>();
        regexs.put(SLING_ID, "Sling ID = ");
        regexs.put(SLING_NAME, "Sling Name = ");
        regexs.put(SLING_DESCRIPTION, "Sling Description = ");
        regexs.put(SLING_HOME, "Sling Home = ");
        regexs.put(SLING_HOME_URL, "Sling Home URL = ");
        regexs.put(RUNMODES, "Run Modes = ");
        parsed = new HashMap<>();

    }

    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return getAuthenticatedGetRequestBuilder("/system/console/status-slingsettings.json").build();
    }

    @Override
    public void execute() {
        super.execute();
        parseResponse();
    }

    private void parseResponse() {
        try {
            JSONArray json = new JSONArray(getHttpResponseAsString());
            for (int i = 0; i < json.length(); i++) {
                for (String key : regexs.keySet()) {
                    String regex = regexs.get(key);
                    if (json.getString(i).contains(regex)) {
                        parsed.put(key, json.getString(i).replace(regex, ""));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSlingID() {
        return parsed.get(SLING_ID);
    }

    public String getSlingName() {
        return parsed.get(SLING_NAME);
    }

    public String getSlingDescription() {
        return parsed.get(SLING_DESCRIPTION);
    }

    public String getSlingHome() {
        return parsed.get(SLING_HOME);
    }

    public String getSlingHomeUrl() {
        return parsed.get(SLING_HOME_URL);
    }

    public Set<String> getRunmodes() {
        String modes = parsed.get(RUNMODES);
        modes.replace("[", "");
        modes.replace("]", "");
        String[] modesArray = modes.split(", ");
        Set<String> result = new HashSet<>();
        Collections.addAll(result, modesArray);
        return result;
    }

}
