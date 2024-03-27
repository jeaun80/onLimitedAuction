package com.example.onlimitedauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnLimitedAuctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnLimitedAuctionApplication.class, args);
    }

}
