package com.innowise.ejbtask.wrapper;

import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.enums.AnswerType;


public class RequestData {
    private RequestBean.InputData inputData;

    private AnswerType answerType;

    public RequestData(RequestBean.InputData inputData, AnswerType answerType) {
        this.inputData = inputData;
        this.answerType = answerType;
        
    }


    public RequestBean.InputData getData() {
        return inputData;
    }


    public String getForward() {
        return inputData.to();
    }


    public AnswerType getAnswerType() {
        return answerType;
    }
}
