package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.interfaces.RequestBean;

public class DefaultData implements RequestBean.OutputData, RequestBean.InputData {

    private String forward;

    private String attributeName;

    private String attributeValue;


    public DefaultData(String forward) {
        this.forward = forward;
        this.attributeName = "";
        this.attributeValue = "";
    }

    public DefaultData(String forward, String attributeName, String attributeValue) {
        this.forward = forward;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public String to() {
        return forward;
    }

    @Override
    public String attributeName() {
        return attributeName;
    }

    @Override
    public Object attributeValue() {
        return attributeValue;
    }
}
