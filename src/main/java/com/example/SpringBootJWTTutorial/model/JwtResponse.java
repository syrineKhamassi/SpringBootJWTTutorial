package com.example.SpringBootJWTTutorial.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private String jwtResponse;

    public JwtResponse() {
    }

    public JwtResponse(String jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    public String getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(String jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}
