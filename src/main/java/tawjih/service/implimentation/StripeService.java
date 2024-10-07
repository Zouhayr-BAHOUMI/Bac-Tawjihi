package tawjih.service.implimentation;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.model.Recu;
import tawjih.repository.RecuRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Autowired
    private RecuRepository recuRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public PaymentIntent createPaymentIntent(Double amount, String currency, String description) throws StripeException {
        Map<String, Object> parametrs = new HashMap<>();
        parametrs.put("amount", (int) (amount * 100));
        parametrs.put("currency", currency);
        parametrs.put("description", description);
        parametrs.put("payment_method_types", Collections.singletonList("card"));

        return PaymentIntent.create(parametrs);
    }

    public Recu savePaimentsDetails(Etudiant etudiant, Pack pack, PaymentIntent paymentIntent) {
        Recu recu = new Recu();
        recu.setEtudiant(etudiant);
        recu.setPack(pack);
        recu.setPaymentIntentId(paymentIntent.getId());
        recu.setStatus(paymentIntent.getStatus());
        recu.setPaymentDate(LocalDate.now());
        recu.setAmountPaid(paymentIntent.getAmount()/100.0);

        return recuRepository.save(recu);
    }

    public PaymentIntent confirmPayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        return paymentIntent;
    }
}
