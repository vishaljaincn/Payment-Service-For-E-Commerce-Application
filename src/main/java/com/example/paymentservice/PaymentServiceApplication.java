package com.example.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the payment service application.
 * This class serves as the entry point for the Spring Boot application,
 * starting the context and enabling automatic configuration.
 */
@SpringBootApplication
public class PaymentServiceApplication {

    /**
     * Main method that starts the Spring Boot application.
     * This method is invoked when the application is run,
     * triggering the Spring Boot framework to initialize the application
     * context and launch the payment service.
     *
     * @param args Command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
