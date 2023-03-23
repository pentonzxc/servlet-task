package com.innowise.ejbtask.beans.interfaces;


import com.innowise.ejbtask.wrapper.RequestAware;

public interface RequestBean {
    OutputData perform(InputData data , RequestAware request);

    interface InputData {
        String to();
    }

    interface OutputData {

        String to();

        String attributeName();

        Object attributeValue();
    }
}
