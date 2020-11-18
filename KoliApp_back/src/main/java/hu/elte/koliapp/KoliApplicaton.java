package hu.elte.koliapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.elte.koliapp.entities.User;
import hu.elte.koliapp.repositories.UserRepository;

@SpringBootApplication
public class KoliApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(KoliApplicaton.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

}
