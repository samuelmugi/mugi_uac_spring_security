package com.mugi.uac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BEWithoutWebAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BEWithoutWebAdapterApplication.class, args);
    }

}
