package com.innowise.ejbtask.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderConfig {
    private static PasswordEncoder passwordEncoder;

    private EncoderConfig() {
    }


    public static synchronized PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder(10);
        }

        return passwordEncoder;
    }
}
