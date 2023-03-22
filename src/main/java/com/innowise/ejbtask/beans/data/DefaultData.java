package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.Bean;

public class DefaultData implements Bean.OutputData, Bean.InputData {

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
    public String forward() {
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
