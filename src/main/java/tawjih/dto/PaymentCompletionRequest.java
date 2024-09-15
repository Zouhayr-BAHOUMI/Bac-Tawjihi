package tawjih.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompletionRequest {

    private String paymentIntent;
    private Integer idEtudiant;
    private Integer idPack;
}
