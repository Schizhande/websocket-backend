package com.schizhande.websocketbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebsocketBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketBackendApplication.class, args);
    }

}
