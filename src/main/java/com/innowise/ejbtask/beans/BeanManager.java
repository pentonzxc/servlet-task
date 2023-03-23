package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.wrapper.RequestAware;
import com.innowise.ejbtask.wrapper.RequestData;
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

    private Map<String, RequestBean> beanMap = new HashMap<>();

    @EJB
    private RegisterBean registerBean;

    @EJB
    private UsersBean usersBean;

    @EJB
    private LoginBean loginBean;

    @EJB
    private RemoveUserBean removeUserBean;


    @EJB
    private LogoutBean logoutBean;


    @PostConstruct
    private void init() {
        beanMap.put("register", registerBean);
        beanMap.put("users", usersBean);
        beanMap.put("login", loginBean);
        beanMap.put("delete", removeUserBean);
        beanMap.put("logout" , logoutBean);
    }


    public void executeBean(String beanType, RequestAware requestAware) throws ServletException, IOException {
        String target = null;
        RequestBean bean = beanMap.get(beanType);

        var requestData = requestWrapper(bean, requestAware);

        var outputData = bean.perform(requestData.getData(), requestAware);

        requestAware.addAttribute(outputData);

        target = requestAware.authorize(outputData.to());

        if (requestData.getAnswerType() == AnswerType.FORWARD) requestAware.forward(target);
        else if (requestData.getAnswerType() == AnswerType.REDIRECT) requestAware.redirect(target);
    }


    private RequestData requestWrapper(RequestBean bean, RequestAware requestAware) {
        RequestData wrapper = null;

        if (bean instanceof RegisterBean) {
            wrapper = new RequestData(InputDataMapper.toRegisterBeanInputData(requestAware), AnswerType.FORWARD);
        }

        else if (bean instanceof UsersBean) {
            wrapper = new RequestData(InputDataMapper.empty(), AnswerType.FORWARD);
        }

        else if (bean instanceof LoginBean) {
            wrapper = new RequestData(InputDataMapper.toLoginBeanInputData(requestAware), AnswerType.FORWARD);
        }

        else if (bean instanceof RemoveUserBean) {
            wrapper = new RequestData(InputDataMapper.toRemoveUserBeanInputData(requestAware), AnswerType.REDIRECT);
        }

        else if(bean instanceof LogoutBean){
            wrapper =  new RequestData(InputDataMapper.toLogoutBeanInputData(requestAware) , AnswerType.FORWARD);
        }

        return wrapper;
    }
}
