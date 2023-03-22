package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.command.RequestAware;
import com.innowise.ejbtask.enums.AnswerType;
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

    @EJB
    private RemoveUserBean removeUserBean;


    @PostConstruct
    private void init() {
        beanMap.put("register", registerBean);
        beanMap.put("users", usersBean);
        beanMap.put("login", loginBean);
        beanMap.put("delete", removeUserBean);
    }


    public void executeBean(String beanType, RequestAware requestAware) throws ServletException, IOException {
        String forward = null;
        Bean bean = beanMap.get(beanType);

        var requestData = requestWrapper(bean, requestAware);

        var outputData = bean.perform(requestData.getData(), requestAware);

        requestAware.addAttribute(outputData);

        forward = outputData.forward();
        forward = requestAware.authorize(forward);

        if(requestData.getAnswerType() == AnswerType.FORWARD) requestAware.forward(forward);
        if(requestData.getAnswerType() == AnswerType.REDIRECT) requestAware.redirect();
    }


    private RequestData requestWrapper(Bean bean, RequestAware requestAware) {
        RequestData wrapper = null;

        if (bean instanceof RegisterBean) {
            wrapper = new RequestData(InputDataMapper.toRegisterBeanInputData(requestAware), AnswerType.FORWARD);
        }

        if (bean instanceof UsersBean) {
            wrapper = new RequestData(InputDataMapper.empty() , AnswerType.FORWARD);
        }

        if (bean instanceof LoginBean) {
            wrapper = new RequestData(InputDataMapper.toLoginBeanInputData(requestAware), AnswerType.FORWARD);
        }

        if (bean instanceof RemoveUserBean) {
            wrapper = new RequestData(InputDataMapper.toRemoveUserBeanInputData(requestAware), AnswerType.REDIRECT);
        }

        return wrapper;
    }
}
