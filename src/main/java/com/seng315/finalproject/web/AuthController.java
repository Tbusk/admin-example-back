package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.security.AuthRequest;
import com.seng315.finalproject.domain.security.JwtResponse;
import com.seng315.finalproject.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public JwtResponse AuthenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            logger.info("User " + authRequest.getUsername() + " logged in.");
            return JwtResponse.builder()
                    .accessToken(jwtService.GenerateToken(authRequest.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
