package tawjih.controller;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tawjih.dto.PaymentRequestDto;
import tawjih.service.implimentation.StripeService;

import java.util.Map;

@RestController
@RequestMapping("/etudiant/paiments")
public class PaimentController {


    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequestDto paymentRequest) {
        try {
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getDescription());
            return ResponseEntity.ok(Map.of("clientSecret", paymentIntent.getClientSecret()).toString());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
