package tawjih.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnoreProperties({"recu", "tests", "pack", "adresse"})
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_pack")
    @JsonIgnoreProperties({"recus", "etudiants"})
    private Pack pack;


    private String paymentIntentId;


    private String status;

    private Double amountPaid;
    private LocalDate paymentDate;
}
