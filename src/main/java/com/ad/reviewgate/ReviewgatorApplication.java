package com.ad.reviewgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewgatorApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(ReviewgatorApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
