package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tawjih.repository.RecuRepository;

@Service
public class StripeService {

    @Autowired
    private RecuRepository recuRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
}
