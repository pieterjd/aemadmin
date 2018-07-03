package com.pieterjd.aemadmin.command.jcr.property;

import com.pieterjd.aemadmin.command.jcr.JcrCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Do something with a property of a jcr node.
 */
public abstract class PropertyCommand extends JcrCommand {

    private String propertyName;
    private String propertyValue;

    public PropertyCommand(String path,String propertyName,String propertyValue) {
        super(path);
        setPropertyName(propertyName);
        setPropertyValue(propertyValue);
    }

    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("path",getPath())
                .append("propertyName",getPropertyName())
                .append("propertyValue",getPropertyValue());
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }


    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }


}
