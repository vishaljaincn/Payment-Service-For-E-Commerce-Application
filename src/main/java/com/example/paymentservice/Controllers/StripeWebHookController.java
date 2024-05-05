package com.example.paymentservice.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handles Stripe webhook notifications.
 * <p>
 * Stripe webhooks are used to receive real-time updates about payment events
 * like successful payments, failed payments, refunds, etc.
 */
@RestController
@RequestMapping("/stripewebhook")
public class StripeWebHookController {

    /**
     * Receives Stripe webhook notifications.
     * <p>
     * This method is typically called by Stripe when a payment event occurs.
     * The request body contains details about the event, such as the order ID,
     * payment status, and other relevant information.
     * // USUALLY WEBHOOKS SHOULD BE AN POST REQUEST, with Order ID and Payment details,
     * // SO THAT U CAN UPDATE THE DATABASE, BUT FOR EASY UNDERSTANDING
     * // I'M USING JUST A EMPTY POST REQUEST
     */
    @PostMapping("/stripe")
    public void receiveWebhook() {
        System.out.println("Webhook method called. Process the Stripe webhook notification here.");
    }
}
