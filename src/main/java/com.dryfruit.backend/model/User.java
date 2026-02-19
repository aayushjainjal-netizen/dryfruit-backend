package com.dryfruit.backend.model;

import lombok.Data;
import java.util.List;

@Data
public class User {

    private String id;
    private String email;
    private String password;
    private List<String> roles;
}
