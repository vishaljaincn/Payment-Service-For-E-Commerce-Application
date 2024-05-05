package com.example.paymentservice.Service;

import com.example.paymentservice.Service.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService {

    @Value("${stripe.key.secret}")
    private String stripeSecretKey;

    /**
     * Generates a Stripe payment link for a given amount, order ID, email, and phone number.
     *
     * @param email Customer's email address
     * @param phoneNumber Customer's phone number
     * @param amount Amount to be charged in Indian Rupees (INR)
     * @param orderId Unique order identifier
     * @return URL of the generated Stripe payment link
     * @throws Exception If any errors occur during payment link creation
     */
    @Override
    public String generatePaymentLink(String email, String phoneNumber, Long amount, String orderId) throws Exception {

        // 1. Set Stripe API key for authentication
        Stripe.apiKey = stripeSecretKey;
        // This sets the API key retrieved from the application properties to
        // authenticate with the Stripe API.

        // 2. Create a generic product for the payment
        Product product = Product.create(
                ProductCreateParams.builder()
                        .setName("Generic Product") // Replace with your product name if applicable
                        .build()
        );
        // This creates a generic product in Stripe, which acts as a placeholder
        // for the item being purchased. You can replace "Generic Product" with
        // a more descriptive name if applicable to your scenario.

        // 3. Create a price object for the specific amount
        Price price = Price.create(
                PriceCreateParams.builder()
                        .setCurrency("inr") // Set currency to INR
                        .setUnitAmount(amount) // Set amount in cents
                        .setProduct(product.getId())
                        .build()
        );
        // This creates a price object associated with the product, specifying the
        // amount to be charged in Indian Rupees (INR). The amount is provided in
        // cents (e.g., 1000 for 10 INR).

        // 4. Create a payment link with the specified price and redirect URL
        PaymentLink paymentLink = PaymentLink.create(
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://scaler.com") // Replace with your desired redirect URL
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
        // This creates a payment link using the previously created price object.
        // It includes a line item specifying the price and quantity (1). The
        // `afterCompletion` configuration defines the behavior after successful
        // payment, redirecting the user to the specified URL (replace with your
        // desired redirect URL).

        return paymentLink.getUrl();
    }
}
