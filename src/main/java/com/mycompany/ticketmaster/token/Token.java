package com.mycompany.ticketmaster.token;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Token {
    private Integer id;
    private String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;
    private String userId;
}
