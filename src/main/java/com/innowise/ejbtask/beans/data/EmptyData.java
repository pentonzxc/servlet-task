package com.innowise.ejbtask.beans.data;

import com.innowise.ejbtask.beans.interfaces.RequestBean;

public class EmptyData implements RequestBean.OutputData, RequestBean.InputData {
    @Override
    public String to() {
        return "";
    }

    @Override
    public String attributeName() {
        return "";
    }

    @Override
    public Object attributeValue() {
        return "";
    }
}
