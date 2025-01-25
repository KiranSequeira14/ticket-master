package com.mycompany.ticketmaster.auth;

import com.mycompany.ticketmaster.model.User;
import com.mycompany.ticketmaster.repository.UserRepository;
import com.mycompany.ticketmaster.security.JwtService;
import com.mycompany.ticketmaster.token.Token;
import com.mycompany.ticketmaster.token.TokenRepository;
import com.mycompany.ticketmaster.token.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.saveAndFlush(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthResponse.builder().accessToken(jwtToken).role(request.getRole()).build();

    }

    public AuthResponse authenticate(AuthRequest request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if (!user.getRole().equals(request.getRole()))
            throw new Exception("Role isn't proper");
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder().role(user.getRole()).accessToken(jwtToken).build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
    }
}