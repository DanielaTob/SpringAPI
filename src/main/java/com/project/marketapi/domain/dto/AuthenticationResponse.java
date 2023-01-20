package com.project.marketapi.domain.dto;

public class AuthenticationResponse {

    private String jwt;

    //Constructor donde llamaremos directamente el jwt - genera el token de seguridad
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
