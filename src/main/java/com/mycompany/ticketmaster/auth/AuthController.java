package com.mycompany.ticketmaster.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;

@RestController
public class AuthController {
    private AuthService authService;

    public ResponseEntity<AuthResponse> signUp(@RequestBody RegisterRequest request) throws AccountException {
        if (!request.getRole().equals("user") && request.getRole().equals("admin")) {
            throw new AccountException("Please provide a proper role");
        }
        return ResponseEntity.ok(authService.registerUser(request));
    }

    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
