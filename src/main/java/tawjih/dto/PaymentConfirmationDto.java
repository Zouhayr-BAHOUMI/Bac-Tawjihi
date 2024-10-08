package tawjih.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentConfirmationDto {
    private String paymentIntentId;
    private Integer etudiantId;
    private Integer packId;
}
