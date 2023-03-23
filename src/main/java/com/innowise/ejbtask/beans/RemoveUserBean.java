package com.innowise.ejbtask.beans;

import com.innowise.ejbtask.beans.data.DefaultData;
import com.innowise.ejbtask.beans.interfaces.RequestBean;
import com.innowise.ejbtask.wrapper.RequestAware;
import com.innowise.ejbtask.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class RemoveUserBean implements RequestBean {

    @EJB
    private UserRepository userRepository;

    @Override
    public OutputData perform(InputData data, RequestAware request) {
        removeUser((DeleteUserData) data);

        return new DefaultData("users");
    }


    public void removeUser(DeleteUserData data) {
        Integer id = data.getId();
        var userOpt = userRepository.findById(id);

        userOpt.ifPresent(usr -> userRepository.remove(usr));
    }


    public static class DeleteUserData implements InputData {
        private Integer id;

        public DeleteUserData(Integer id) {
            this.id = id;
        }

        @Override
        public String to() {
            return null;
        }

        public Integer getId() {
            return id;
        }
    }
}
