package com.example.SpringBootJWTTutorial.controller;

import com.example.SpringBootJWTTutorial.config.JwtTokenUtil;
import com.example.SpringBootJWTTutorial.model.JwtRequest;
import com.example.SpringBootJWTTutorial.model.JwtResponse;
import com.example.SpringBootJWTTutorial.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException ex) {
            throw new Exception("USER_DISABLED" , ex);
        } catch (BadCredentialsException ex) {
            throw new Exception("BAD_CREDENTIALS" , ex);
        }
    }

}
