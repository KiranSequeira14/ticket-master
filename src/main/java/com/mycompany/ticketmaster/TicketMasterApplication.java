package com.mycompany.ticketmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TicketMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketMasterApplication.class, args);
	}

}
