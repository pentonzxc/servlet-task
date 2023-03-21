package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.beans.data.RequestBeanDataWrapper;
import com.innowise.ejbtask.command.RequestAware;
import com.innowise.ejbtask.mapper.InputDataMapper;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class BeanManager {

    private Map<String, Bean> beanMap = new HashMap<>();

    @EJB
    private RegisterBean registerBean;

    @EJB
    private UsersBean usersBean;

    @EJB
    private LoginBean loginBean;


    @PostConstruct
    private void init() {
        beanMap.put("register", registerBean);
        beanMap.put("users", usersBean);
        beanMap.put("login", loginBean);
    }


    public void executeBean(String beanType, RequestAware requestAware) throws ServletException, IOException {
        String forward = null;
        Bean bean = beanMap.get(beanType);

        var dataWrapper = dataWrapper(bean, requestAware);
        forward = dataWrapper.getForward();

        var outputData = bean.perform(dataWrapper.getData());

        requestAware.addAttribute(outputData);

        if ("login".equals(beanType)) {
            forward = requestAware.authenticate(forward);
        }

        forward = requestAware.authorize(forward);

        requestAware.forward(forward);
    }


    private RequestBeanDataWrapper dataWrapper(Bean bean, RequestAware requestAware) {
        RequestBeanDataWrapper wrapper = null;

        if (bean instanceof RegisterBean) {
            wrapper = new RequestBeanDataWrapper(InputDataMapper.toRegisterBeanInputData(requestAware), "index");
        }

        if (bean instanceof UsersBean) {
            wrapper = new RequestBeanDataWrapper(InputDataMapper.empty(), "list");
        }

        if (bean instanceof LoginBean) {
            wrapper = new RequestBeanDataWrapper(InputDataMapper.toLoginBeanInputData(requestAware), "index");
        }

        return wrapper;
    }
}
