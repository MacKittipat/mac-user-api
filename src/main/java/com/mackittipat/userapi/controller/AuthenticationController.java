package com.mackittipat.userapi.controller;

import com.mackittipat.userapi.dto.AuthenticationRequest;
import com.mackittipat.userapi.dto.AuthenticationResponse;
import com.mackittipat.userapi.dto.User;
import com.mackittipat.userapi.repository.UserRepository;
import com.mackittipat.userapi.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("api")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        String token;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(), authenticationRequest
                            .getPassword()));

            User user = userRepository.findByUsername(authenticationRequest.getUsername());
            user.setPassword(null);

            token = JwtUtil.generateToken(authentication, user);
        } catch (BadCredentialsException e) {
            String errorMsg = "User " + authenticationRequest.getUsername() + " try to login with incorrect password";
            log.info(errorMsg);
            throw new BadCredentialsException(errorMsg);
        }
        return AuthenticationResponse.builder().token(token).build();
    }

}
