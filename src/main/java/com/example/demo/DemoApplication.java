package com.example.demo;

import com.example.demo.entities.Account;
import com.example.demo.entities.User;
import com.example.demo.repos.AccountRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    InitializingBean loadData() {
        return () -> {
            User userSender = userRepo.save(new User(1, "Vlad", "Chivu"));
            User userReceiver = userRepo.save(new User(2, "Marcus", "Heisenberg"));
            accountRepo.save(new Account(1, "A100", 100.0, userSender));
            accountRepo.save(new Account(2, "A101", 100.0, userReceiver));
        };
    }

}
