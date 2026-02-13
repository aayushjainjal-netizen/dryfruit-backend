package com.dryfruit.backend.model;

public class AuthResponse {

    private String token;
    private String email;
    private Object roles;

    public AuthResponse(String token, String email, Object roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }
    public Object getRoles() { return roles; }

    public void setToken(String token) { this.token = token; }
    public void setEmail(String email) { this.email = email; }
    public void setRoles(Object roles) { this.roles = roles; }
}
