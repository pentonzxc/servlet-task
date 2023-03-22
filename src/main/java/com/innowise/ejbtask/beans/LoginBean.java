package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.User;
import com.innowise.ejbtask.beans.data.DefaultData;
import com.innowise.ejbtask.beans.data.ErrorData;
import com.innowise.ejbtask.command.RequestAware;
import com.innowise.ejbtask.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;


@Stateless
@LocalBean
public class LoginBean implements Bean {

    @EJB
    private UserRepository userRepository;

    @Override
    public OutputData perform(InputData data, RequestAware request) {
        var user = login((LoginData) data);

        if (user == null) {
            return new ErrorData("login", "Invalid credentials");
        }

        request.getRequest().getSession(false).setAttribute("role", user.getRole().name());


        return new DefaultData(data.forward(), "", "");
    }


    public User login(LoginData data) {
        var userOpt = userRepository.findByName(data.getName());

        return userOpt.isPresent() && validPassword(data, userOpt.get()) ? userOpt.get() : null;
    }


    public boolean validPassword(LoginData data, User user) {
        return data.getPassword().equals(user.getPassword());
    }


    public static class LoginData implements InputData {

        private String name;

        private String password;

        private String forward;


        public LoginData(String name, String password, String forward) {
            this.name = name;
            this.password = password;
            this.forward = forward;
        }


        public String getName() {
            return name;
        }


        public String getPassword() {
            return password;
        }


        @Override
        public String forward() {
            return forward;
        }
    }
}
