package tawjih.controller;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tawjih.dto.PaymentConfirmationDto;
import tawjih.dto.PaymentRequestDto;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.model.Recu;
import tawjih.service.implimentation.EtudiantService;
import tawjih.service.implimentation.PackService;
import tawjih.service.implimentation.StripeService;

import java.util.Map;

@RestController
@RequestMapping("/etudiant/paiments")
@RequiredArgsConstructor
public class PaimentController {

    private final StripeService stripeService;

    private final EtudiantService etudiantService;

    private final PackService packService;

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
    public ResponseEntity<?> confirmPayment(@RequestBody PaymentConfirmationDto confirmationDto) {
        try {
            PaymentIntent paymentIntent = stripeService.confirmPayment(confirmationDto.getPaymentIntentId());

            Etudiant etudiant = etudiantService.getEtudiant(confirmationDto.getEtudiantId());
            Pack pack = packService.getPack(confirmationDto.getPackId());

            boolean hasAlreadyPaid = stripeService.hasEtudiantAlreadyPaid(etudiant, pack);
            if (hasAlreadyPaid) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "L'étudiant a déjà payé pour ce pack."));
            }

            Recu recu = stripeService.savePaimentsDetails(etudiant, pack, paymentIntent);
            return ResponseEntity.ok(recu);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
