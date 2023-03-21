package com.innowise.ejbtask.beans;


import com.innowise.ejbtask.User;
import com.innowise.ejbtask.beans.data.EmptyData;
import com.innowise.ejbtask.beans.data.ErrorData;
import com.innowise.ejbtask.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;


@Stateless
@LocalBean
public class RegisterBean implements Bean {

    @EJB
    private UserRepository userRepository;

    @Override
    public OutputData perform(InputData data) {
        return register((RegisterData) data);
    }

    private OutputData register(RegisterData data) {
        if (userRepository.findByName(data.getName()).isPresent()) {
                return new ErrorData("Name already in use");
        }

        userRepository.save(new User(data.getName(), data.getPassword()));

        return new EmptyData();
    }


    public static class RegisterData implements InputData {

        private String name;

        private String password;

        public RegisterData(String name, String password) {
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
}
