package com.brave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author jbrave
 */
@EnableAsync
@SpringBootApplication
public class UtilApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilApplication.class, args);
    }

}
