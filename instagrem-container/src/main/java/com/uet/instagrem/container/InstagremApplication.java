package com.uet.instagrem.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.uet.instagrem")
public class InstagremApplication {
    public static void main(String[] args) {
        SpringApplication.run(InstagremApplication.class, args);
    }
}