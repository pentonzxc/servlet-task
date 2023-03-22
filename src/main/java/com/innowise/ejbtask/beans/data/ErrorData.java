package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.Bean;

public class ErrorData implements Bean.OutputData {


    private String errorMsg;

    private String forward;


    public ErrorData(String forward, String errorMsg) {
        this.forward = forward;
        this.errorMsg = errorMsg;
    }

    @Override
    public String forward() {
        return forward;
    }

    @Override
    public String attributeName() {
        return "error";
    }

    @Override
    public Object attributeValue() {
        return errorMsg;
    }
}
