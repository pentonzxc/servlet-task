package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.User;
import com.innowise.ejbtask.repository.UserRepository;
import com.innowise.ejbtask.security.Role;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.util.Optional;


@Stateless
@LocalBean
public class LoginBean implements Bean {

    @EJB
    private UserRepository userRepository;

    @Override
    public OutputData perform(InputData data) {
        var user = login((LoginData) data);

        return new AuthenticationData(user.map(User::getRole).orElse(Role.NONE));
    }


    public Optional<User> login(LoginData loginData) {
        return userRepository.findByName(loginData.getName());
    }


    public static class LoginData implements InputData {

        private String name;

        private String password;

        public LoginData(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }
    }


    public static class AuthenticationData implements OutputData {

        private Role role;


        public AuthenticationData(Role role) {
            this.role = role;
        }

        @Override
        public String attributeName() {
            return "role";
        }

        @Override
        public Object attributeValue() {
            return role.name();
        }


    }
}
