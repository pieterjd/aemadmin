package com.pieterjd.aemadmin.command;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by pdrouill on 21/06/2017.
 *
 * Abstract class containing all information to connect to your AEM Stack.
 */
public abstract class AbstractCommand {

    private boolean executed;
    private boolean success;




    public AbstractCommand(){

        setExecuted(false);
        setSuccess(false);
    }







    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean hasExecuted) {
        this.executed = hasExecuted;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    /**
     * Execute the command. This method needs implementation in subclasses.
     */
    public abstract void execute();

    /**
     * Check if the executed command was executed and successful
     * @return true if all went well, false otherwise
     */
    public  boolean isSuccessfullyExecuted(){
        return isExecuted() && isSuccess();
    }

    protected ToStringBuilder getToStringBuilder(){
        return new ToStringBuilder(this)
                .append("executed",isExecuted())
                .append("success",isSuccess());
    }
    public String toString(){
        return getToStringBuilder().build();
    }

}
