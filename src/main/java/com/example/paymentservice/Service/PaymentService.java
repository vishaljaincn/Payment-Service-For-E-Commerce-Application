package com.example.paymentservice.Service;

/**
 * This interface defines the contract for a payment service.
 * Implementations of this interface should provide functionalities for initiating and processing payments.
 */
public interface PaymentService {

    /**
     * Generates a payment link for the given payment details.
     *
     * @param email Email address of the payer.
     * @param phoneNumber Phone number of the payer (optional).
     * @param amount Amount to be charged in the smallest currency unit (e.g., cents for USD).
     * @param orderId Unique identifier for the order associated with the payment.
     * @return The generated payment link.
     * @throws Exception If there is an error during payment link generation.
     */
    String generatePaymentLink(String email, String phoneNumber, Long amount, String orderId) throws Exception;
}
