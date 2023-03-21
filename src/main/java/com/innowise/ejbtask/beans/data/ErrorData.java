package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.Bean;

public class ErrorData implements Bean.OutputData {


    private String errorMsg;


    public ErrorData(String errorMsg) {
        this.errorMsg = errorMsg;
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
