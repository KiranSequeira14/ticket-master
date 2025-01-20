package com.mycompany.ticketmaster.util;

import java.security.SecureRandom;

public class EventIdGenerator {
    private static final String PREFIX = "E";
    private static final int LENGTH = 5;
    private static final SecureRandom random = new SecureRandom();

    public static String generateEventId() {
        int number = random.nextInt((int) Math.pow(10, LENGTH));
        return PREFIX + String.format("%05d", number);
    }
}
