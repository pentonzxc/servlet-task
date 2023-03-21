package com.innowise.ejbtask.beans;


public interface Bean {
    OutputData perform(InputData data);

    interface InputData {
    }

    interface OutputData {
        String attributeName();

        Object attributeValue();
    }
}
