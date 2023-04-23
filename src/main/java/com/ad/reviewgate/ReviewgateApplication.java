package com.ad.reviewgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewgateApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ReviewgateApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
