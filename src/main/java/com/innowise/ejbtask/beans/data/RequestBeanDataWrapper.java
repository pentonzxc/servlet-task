package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.Bean;

public class RequestBeanDataWrapper {
    private Bean.InputData inputData;

    private String forwardTo;

    public RequestBeanDataWrapper(Bean.InputData inputData, String forwardTo) {
        this.inputData = inputData;
        this.forwardTo = forwardTo;
    }


    public String getForward() {
        return forwardTo;
    }


    public Bean.InputData getData() {
        return inputData;
    }


    public void setInputData(Bean.InputData inputData) {
        this.inputData = inputData;
    }


    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }
}
