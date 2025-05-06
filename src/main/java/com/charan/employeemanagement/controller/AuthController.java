package com.charan.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authInputToken =
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authInputToken);

        User user = (User) authentication.getPrincipal();

        // Extract the first role (assuming one role per user)
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.replace("ROLE_", "")) // Optional: remove "ROLE_" prefix
                .findFirst()
                .orElse("UNKNOWN");

        return ResponseEntity.ok(new LoginResponse(role));
    }
}
