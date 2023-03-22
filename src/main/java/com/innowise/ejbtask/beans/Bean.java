package com.innowise.ejbtask.beans;


import com.innowise.ejbtask.command.RequestAware;

public interface Bean {
    OutputData perform(InputData data , RequestAware request);

    interface InputData {
        String forward();
    }

    interface OutputData {

        String forward();

        String attributeName();

        Object attributeValue();
    }
}
