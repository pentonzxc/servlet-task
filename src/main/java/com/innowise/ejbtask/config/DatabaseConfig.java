package com.innowise.ejbtask.config;

import com.innowise.ejbtask.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class DatabaseConfig {
    private static SessionFactory factory;

    private DatabaseConfig() {
    }


    public static synchronized SessionFactory buildSessionFactory() {
        if (factory == null) {
            var config = new Configuration();
            config.configure("hibernate.cfg.xml");
            var service = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

            factory = config.addAnnotatedClass(User.class).buildSessionFactory(service);
        }
        return factory;
    }
}
