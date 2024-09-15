package tawjih.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.PaymentStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recus")
public class Recu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_pack")
    private Pack pack;


    private String paymentIntentId;


    private String status;

    private Double amountPaid;
    private LocalDate paymentDate;
}
