package com.example.paymentservice.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class configures the Razorpay client for making payments.
 */
@Configuration
public class RazorpayConfig {

    /**
     * Injected Razorpay Key ID from application properties.
     */
    @Value(value = "${razorpay.key.id}")
    private String razorpayKeyId;

    /**
     * Injected Razorpay Key Secret from application properties.
     */
    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    /**
     * Creates a new Razorpay client instance using the injected key ID and key secret.
     *
     * @return A new RazorpayClient instance.
     * @throws RazorpayException If there is an error creating the client.
     */
    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayKeyId, razorpayKeySecret);
    }

}

/*
Purpose of @Configuration Annotation:

In Spring Framework, the @Configuration annotation serves the following key purpose:

Marks a class as a source of bean definitions:
By applying this annotation, you essentially tell the
Spring container that this class holds configurations for creating objects (beans) that will be managed
by the Spring application context.

Enables bean creation using @Bean methods:
The @Configuration annotation allows you to define beans
using methods within the class that are annotated with @Bean. These methods become responsible for
creating, configuring, and initializing the beans that the Spring application will use.
 */