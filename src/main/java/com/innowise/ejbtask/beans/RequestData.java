package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.enums.AnswerType;

public class RequestData {
    private Bean.InputData inputData;

    private AnswerType answerType;

    public RequestData(Bean.InputData inputData, AnswerType answerType) {
        this.inputData = inputData;
        this.answerType = answerType;
    }


    public Bean.InputData getData() {
        return inputData;
    }

    public String getForward() {
        return inputData.forward();
    }


    public AnswerType getAnswerType() {
        return answerType;
    }
}
