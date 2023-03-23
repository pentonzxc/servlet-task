package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.entity.User;
import com.innowise.ejbtask.wrapper.RequestAware;
import com.innowise.ejbtask.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
@LocalBean
public class UsersBean implements RequestBean {

    @EJB
    private UserRepository userRepository;


    @Override
    public UserListData perform(InputData data, RequestAware requestAware) {
        return new UserListData(userRepository.findAll());
    }


    public static class UserListData implements OutputData {

        private List<User> users;

        public UserListData(List<User> users) {
            this.users = users;
        }

        @Override
        public String to() {
            return "list";
        }

        @Override
        public String attributeName() {
            return "users";
        }

        @Override
        public Object attributeValue() {
            return users;
        }
    }
}
