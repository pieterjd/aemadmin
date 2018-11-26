package com.pieterjd.aemadmin.command.crx.node;

import com.pieterjd.aemadmin.command.crx.CrxCommand;
import com.pieterjd.aemadmin.command.crx.property.SetPropertyCommand;
import org.apache.http.client.methods.HttpUriRequest;
import com.github.tsohr.JSONException;
import com.github.tsohr.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pdrouill on 6/07/2017.
 */
public class CopyNodeCommand extends CrxCommand {
    private final String[] excludedProperties = {};
    //create list with properties to exclude
    private List<String> excluded = Arrays.asList(excludedProperties);
    private String destination;

    public CopyNodeCommand(String path, String destination) {
        super(path);
        setDestination(destination);

    }



    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    @Override
    public HttpUriRequest getRequest() throws URISyntaxException {
        return null;
    }

    @Override
    public void execute() {
            setSuccess(false);

            recursiveCopy(getPath(),getDestination());
            setExecuted(true);


    }

    /**
     * processes the source to JSON Object and recursively copies to the destination

     */
    private void recursiveCopy(String source, String destination) {
        System.out.println("copy from "+source+" to "+destination);
        //* get source node
        GetNodeCommand gnc = new GetNodeCommand(source);
        //System.out.println("getting source node in JSON");
        gnc.execute();
        setSuccess(gnc.isSuccess()&&isSuccess());
        String responseString = null;
        try {
            responseString = gnc.getHttpResponseAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(responseString);
        JSONObject gncResult = new JSONObject(responseString);
        System.out.println(responseString);
        System.out.println(gncResult);
        //* create destination node
        if(gncResult != null) {
            CreateNodeCommand cnc = new CreateNodeCommand(destination, gncResult.getString("jcr:primaryType"));
            setSuccess(cnc.isSuccess()&&isSuccess());
            cnc.execute();
            //childnodes should be copied after setting all properties to keep the original order
            List<String> childNodes = new ArrayList<>();
            // copy all properties except the excluded ones and the ones starting with :
            for (Iterator<String> iter = gncResult.keys(); iter.hasNext(); ) {
                String key = iter.next();
                if (!key.startsWith(":") && !excluded.contains(key)) {
                    //set property with name key
                    System.out.println("getting property: " + key);
                    String value = null;
                    try {
                        value = gncResult.getString(key);
                    } catch (JSONException jse) {
                        //exception thrown when key is a JSON object in itself
                        //so do a recursive call
                        //System.out.println("childnode looks like:\n"+gncResult.getJSONObject(key));

                        childNodes.add(key);
                    }
                    SetPropertyCommand spc = new SetPropertyCommand(destination, key, value);
                    if (value != null) {
                        spc.execute();
                        //System.out.println(spc);
                    }


                }
            }
            //recursively copy child nodes
            for (int i = 0; i < childNodes.size(); i++) {
                String node = childNodes.get(i);
                recursiveCopy(source + "/" + node, destination + "/" + node);

            }
        }
    }
}
