package com.pieterjd.aemadmin.command.jcr;

import com.pieterjd.aemadmin.command.HttpRequestCommand;

/**
 * Created by pdrouill on 22/06/2017.
 */
public abstract class JcrCommand extends HttpRequestCommand {


    private String path;

    /**
     * Path of the jcr node. this path should start with a "/"
     * @param path the path to the node you want to interact with
     */
    public JcrCommand(String path) {
        super();
        setPath(path);
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
