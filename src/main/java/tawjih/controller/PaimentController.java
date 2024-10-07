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
import tawjih.dto.PaymentConfirmationDto;
import tawjih.dto.PaymentRequestDto;
import tawjih.model.Recu;
import tawjih.service.implimentation.StripeService;

import java.util.Map;

@RestController
@RequestMapping("/etudiant/paiments")
public class PaimentController {


    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentRequestDto paymentRequest) {
        try {
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getDescription());

            Map<String, String> response = Map.of("clientSecret", paymentIntent.getClientSecret());
            return ResponseEntity.ok(response);

        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<Recu> confirmPayment(@RequestBody PaymentConfirmationDto confirmationDto) {
        try {
            PaymentIntent paymentIntent = stripeService.confirmPayment(confirmationDto.getPaymentIntentId());

            Recu recu = stripeService.savePaimentsDetails(confirmationDto.getEtudiant(), confirmationDto.getPack(), paymentIntent);
            return ResponseEntity.ok(recu);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
