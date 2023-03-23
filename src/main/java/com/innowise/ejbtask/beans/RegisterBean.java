package com.innowise.ejbtask.beans;


import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.entity.User;
import com.innowise.ejbtask.beans.data.DefaultData;
import com.innowise.ejbtask.beans.data.ErrorData;
import com.innowise.ejbtask.wrapper.RequestAware;
import com.innowise.ejbtask.enums.Role;
import com.innowise.ejbtask.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import static com.innowise.ejbtask.config.EncoderConfig.passwordEncoder;


@Stateless
@LocalBean
public class RegisterBean implements RequestBean {

    @EJB
    private UserRepository userRepository;

    @Override
    public OutputData perform(InputData data, RequestAware requestAware) {
        return register((RegisterData) data);
    }


    private OutputData register(RegisterData data) {
        if (userRepository.findByName(data.getName()).isPresent()) {
            return new ErrorData("register", "Name already in use");
        }

        User user = new User(data.getName(), passwordEncoder().encode(data.getPassword()));
        user.setCountry(data.getCountry());
        user.setEmail(data.getEmail());
        user.setRole(Role.USER);

        userRepository.save(user);

        return new DefaultData("index", "", "");
    }


    public static class RegisterData implements InputData {
        private String name;

        private String password;

        private String email;

        private String country;

        private String forward;

        public RegisterData(String name, String password, String email, String country, String forward) {
            this.name = name;
            this.password = password;
            this.email = email;
            this.country = country;
            this.forward = forward;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getCountry() {
            return country;
        }

        @Override
        public String to() {
            return forward;
        }
    }
}
