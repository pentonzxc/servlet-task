package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.interfaces.RequestBean;

public class ErrorData implements RequestBean.OutputData {

    private String errorMsg;

    private String forward;

    public ErrorData(String forward, String errorMsg) {
        this.forward = forward;
        this.errorMsg = errorMsg;
    }

    @Override
    public String to() {
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
