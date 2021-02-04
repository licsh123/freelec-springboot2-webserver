package com.chuchu.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditting 활성화
@SpringBootApplication

public class Application{
    public static void main(String[] args){
            SpringApplication.run(Application.class, args);
    }
}

