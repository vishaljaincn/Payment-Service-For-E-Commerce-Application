package com.example.paymentservice.Controllers;

import com.example.paymentservice.Dtos.InitiatePaymentRequestDto;
import com.example.paymentservice.Service.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class exposes REST API endpoints for payment operations.
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    /**
     * Injected PaymentService instance using Spring Qualifier.
     */
    private final PaymentService paymentService;

    /**
     * Constructor to inject the PaymentService implementation.
     *
     * @param paymentService The PaymentService implementation (e.g., StripePaymentService).
     */
    public PaymentController(@Qualifier("stripe") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // @Primary - A bean should be given preference when multiple candidates are qualified
    // @Qualifier - A specific bean should be auto-wired (name of the bean can be used as qualifier)
    // (REMEMBER) @Qualifier has higher priority then @Primary

    /**
     * Initiates a payment and returns the generated payment link.
     *
     * @param initiatePaymentRequestDto Payment initiation request data.
     * @return The generated payment link.
     * @throws Exception If there is an error during payment initiation.
     */
    @PostMapping("/initiate")
    public String initiatePayment(
            @RequestBody InitiatePaymentRequestDto
                    initiatePaymentRequestDto) throws Exception {
        return paymentService.generatePaymentLink(
                initiatePaymentRequestDto.getEmail(),
                initiatePaymentRequestDto.getPhoneNumber(),
                initiatePaymentRequestDto.getAmount(),
                initiatePaymentRequestDto.getOrderId());
    }

}
