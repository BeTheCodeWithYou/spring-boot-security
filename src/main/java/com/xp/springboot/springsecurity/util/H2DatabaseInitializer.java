package com.xp.springboot.springsecurity.util;

import com.xp.springboot.springsecurity.model.UserEntity;
import com.xp.springboot.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class H2DatabaseInitializer implements ApplicationRunner {

    private UserRepository userRepository;

    public H2DatabaseInitializer(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("neeraj");
        userEntity.setPassword("admin");
        userEntity.setActive(true);
        userEntity.setRoles("ADMIN_ROLE");

        userRepository.save(userEntity);

    }
}