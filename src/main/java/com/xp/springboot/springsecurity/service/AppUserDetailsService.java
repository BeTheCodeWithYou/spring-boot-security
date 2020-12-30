package com.xp.springboot.springsecurity.service;

import com.xp.springboot.springsecurity.model.AppUserDetail;
import com.xp.springboot.springsecurity.model.UserEntity;
import com.xp.springboot.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUserName(userName);

        userEntity.orElseThrow(() -> new UsernameNotFoundException("User Not found "+ userName));

        return userEntity.map(AppUserDetail::new).get();
    }
}
