package com.management.hotels;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class HotelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsApplication.class, args);
    }

}