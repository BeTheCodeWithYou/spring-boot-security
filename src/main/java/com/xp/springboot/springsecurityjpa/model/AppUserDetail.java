package com.xp.springboot.springsecurityjpa.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserDetail implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public AppUserDetail(String thisUserName){
        this.username = thisUserName;
    }

    public AppUserDetail(){

    }

    public AppUserDetail(UserEntity userEntity){
        this.username = userEntity.getUserName();
        this.password = userEntity.getPassword();
        this.active = userEntity.isActive();
        this.authorities = Arrays.stream(userEntity.getRoles().split(","))
                                     .map(SimpleGrantedAuthority::new)
                                     .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
