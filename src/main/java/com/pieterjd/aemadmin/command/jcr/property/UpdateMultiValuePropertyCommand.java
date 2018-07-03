package com.pieterjd.aemadmin.command.jcr.property;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class UpdateMultiValuePropertyCommand extends PropertyCommand{
    private String propertyType;

    public UpdateMultiValuePropertyCommand(String path,String propertyName,String propertyValue) {
        this(path,propertyName,propertyValue,"String");
    }

    public UpdateMultiValuePropertyCommand(String path,String propertyName,String propertyValue,String propertyType) {
        super(path,propertyName,propertyValue);
        setPropertyType(propertyType);
    }
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }


    @Override
    protected ToStringBuilder getToStringBuilder() {
        return super.getToStringBuilder()
                .append("path",getPath())
                .append("multiValuePropertyName",getPropertyName())
                .append("addedPropertyValue",getPropertyValue());
    }
}
