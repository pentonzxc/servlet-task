package com.innowise.ejbtask.beans;

public class RequestData {
    private Bean.InputData inputData;

    private String forwardTo;

    public RequestData(Bean.InputData inputData) {
        this.inputData = inputData;
    }


    public Bean.InputData getData() {
        return inputData;
    }


}
