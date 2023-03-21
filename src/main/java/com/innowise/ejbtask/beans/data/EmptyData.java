package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.Bean;

public class EmptyData implements Bean.OutputData, Bean.InputData {
    @Override
    public String attributeName() {
        return "";
    }

    @Override
    public Object attributeValue() {
        return "";
    }
}
