package com.example.paymentservice.Service;

import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Service("razorpay")
public class RazorpayPaymentService implements PaymentService {

    private final RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.razorpayClient = razorpayClient;
    }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    /**
     * Generates a Razorpay payment link for the given details.
     *
     * @param email Customer's email address
     * @param phoneNumber Customer's phone number
     * @param amount Amount to be paid (in paise)
     * @param orderId Order ID for reference
     * @return String representation of the created PaymentLink object
     * @throws RazorpayException If any error occurs during payment link creation
     */
    @Override
    public String generatePaymentLink(String email, String phoneNumber, Long amount, String orderId) throws RazorpayException {

        // 1. Create a JSONObject to hold payment link details
        JSONObject paymentLinkRequest = new JSONObject();

        // 1.1. Set payment amount (in paise)
        paymentLinkRequest.put("amount", amount);
        // This specifies the total amount the customer needs to pay, represented in the
        // smallest currency unit (paise for INR).

        // 1.2. Set currency to INR
        paymentLinkRequest.put("currency", "INR");
        // This specifies the currency used for the payment.

        // 1.3. Allow partial payments
        paymentLinkRequest.put("accept_partial", true);
        // This enables customers to make partial payments towards the total amount.

        // 1.4. Set minimum partial payment amount
        paymentLinkRequest.put("first_min_partial_amount", 100);
        // This defines the minimum amount a customer can pay in the first partial payment.

        // 1.5. Set payment link expiry time
        paymentLinkRequest.put("expire_by", 1735671600); // Replace with desired timestamp
        // This sets the timestamp after which the payment link becomes invalid.

        // 1.6. Set reference ID for the order
        paymentLinkRequest.put("reference_id", orderId);
        // This assigns a unique identifier to the payment link for tracking purposes.

        // 1.7. Set payment description
        paymentLinkRequest.put("description", "Payment for policy no #" + orderId);
        // This provides a brief description of the payment purpose.

        // 2. Create a JSONObject for customer details
        JSONObject customer = new JSONObject();
        customer.put("name", "+919000090000"); // Replace with actual customer name
        customer.put("contact", "Gaurav Kumar"); // Replace with actual customer name
        customer.put("email", email);

        // 2.1. Add customer details to the payment link request
        paymentLinkRequest.put("customer", customer);
        // This associates customer information with the payment link.

        // 3. Create a JSONObject for notification settings
        JSONObject notify = new JSONObject();
        notify.put("sms", true); // Enable SMS notification
        notify.put("email", true); // Enable email notification

        // 3.1. Add notification settings to the payment link request
        paymentLinkRequest.put("notify", notify);
        // This enables sending payment status updates to the customer via SMS and email.

        // 4. Enable payment reminders
        paymentLinkRequest.put("reminder_enable", true);
        // This allows sending automatic reminders to the customer if the payment is not completed.

        // 5. Create a JSONObject for additional notes
        JSONObject notes = new JSONObject();
        notes.put("policy_name", "Jeevan Bima"); // Replace with relevant policy name
        notes.put("notes_key_1", email);
        notes.put("notes_key_2", phoneNumber);

        // 5.1. Add additional notes to the payment link request
        paymentLinkRequest.put("notes", notes);
        // This allows storing additional information related to the payment.

        // 6. Set callback URL and method for payment status updates
        paymentLinkRequest.put("callback_url", "https://www.scaler.com");
        paymentLinkRequest.put("callback_method", "get");
        // This defines the URL and HTTP method (GET) where Razorpay will send payment
        // status updates.

        // 7. Create the payment link using RazorpayClient
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        // 8. Return the string representation of the created PaymentLink object
        return payment.toString();
    }
}
