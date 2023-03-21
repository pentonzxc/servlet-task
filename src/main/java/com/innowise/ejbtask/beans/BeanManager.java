package com.innowise.ejbtask.beans;

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

        var requestData = requestWrapper(bean, requestAware);

        var outputData = bean.perform(requestData.getData() , requestAware);

        requestAware.addAttribute(outputData);

//        if ("login".equals(beanType)) {
//            forward = requestAware.authenticate(forward);
//        }
//
//        forward = requestAware.authorize(forward);

        requestAware.forward(forward);
    }


    private RequestData requestWrapper(Bean bean, RequestAware requestAware) {
        RequestData wrapper = null;

        if (bean instanceof RegisterBean) {
            wrapper = new RequestData(InputDataMapper.toRegisterBeanInputData(requestAware));
//            wrapper = new RequestBean(InputDataMapper.toRegisterBeanInputData(requestAware), "index");
        }

        if (bean instanceof UsersBean) {
//            wrapper = new RequestBean(InputDataMapper.empty(), "list");
            wrapper = new RequestData(InputDataMapper.empty());
        }

        if (bean instanceof LoginBean) {
//            wrapper = new RequestBean(InputDataMapper.toLoginBeanInputData(requestAware), "index");
            wrapper = new RequestData(InputDataMapper.toLoginBeanInputData(requestAware));
        }

        return wrapper;
    }
}
