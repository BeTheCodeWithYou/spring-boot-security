package com.xp.springboot.springsecurity.controller;

import com.xp.springboot.springsecurity.model.AuthenticationRequest;
import com.xp.springboot.springsecurity.model.AuthenticationResponse;
import com.xp.springboot.springsecurity.service.AppUserDetailsService;
import com.xp.springboot.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring security app";
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws  Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("invalid username or password");
        }

        final UserDetails userDetails= appUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
