package com.innowise.ejbtask.config;

import com.innowise.ejbtask.entity.User;
import com.innowise.ejbtask.enums.Role;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class AppInit {

    @PostConstruct
    public void init() {
        var factory = DatabaseConfig.buildSessionFactory();
        var encoder = EncoderConfig.passwordEncoder();

        try (var session = factory.openSession()) {
            var transaction = session.beginTransaction();

            User admin = new User();
            admin.setName("admin");
            admin.setPassword(encoder.encode("admin"));
            admin.setEmail("admin@gmail.com");
            admin.setCountry("NONE");
            admin.setRole(Role.ADMIN);

            session.persist(admin);

            transaction.commit();
        }
    }
}
