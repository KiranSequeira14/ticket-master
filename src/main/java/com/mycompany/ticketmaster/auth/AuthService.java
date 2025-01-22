package com.mycompany.ticketmaster.auth;

import com.mycompany.ticketmaster.model.User;
import com.mycompany.ticketmaster.repository.UserRepository;
import com.mycompany.ticketmaster.security.JwtService;
import com.mycompany.ticketmaster.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Environment environment;

    public AuthResponse registerUser(RegisterRequest request) throws AccountException {
        Optional<User> registeredUser = userRepository.findByEmail(request.getEmail());
        if (registeredUser.isPresent()) {
            throw new AccountException("Account already exists for the email " + request.getEmail());
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        User savedUser = userRepository.saveAndFlush(user);
//        var jwtToken = jwtService.generateToken(user);
//        saveUserToken(savedUser, jwtToken);

//        return AuthResponse.builder().accessToken(jwtToken).role(request.getRole()).build();
        return null;
    }

    public AuthResponse authenticate(AuthRequest request) {
        return null;
    }
}
