package tawjih.service.implimentation;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tawjih.repository.RecuRepository;

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
}
