package com.example.paymentservice.Dtos;

import lombok.Data;

/**
 * This class represents a data transfer object (DTO) for initiating a payment.
 * It holds the necessary information required to initiate a payment.
 */
@Data
public class InitiatePaymentRequestDto {

    /**
     * Email address of the payer.
     */
    private String email;

    /**
     * Phone number of the payer (optional).
     */
    private String phoneNumber;

    /**
     * Amount to be charged in the smallest currency unit (e.g., cents for USD).
     */
    private Long amount;

    /**
     * Unique identifier for the order associated with the payment.
     */
    private String orderId;
}
