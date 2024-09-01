package tawjih.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.TypeEtablissement;
import tawjih.enums.TypePack;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomPack", nullable = false)
    private String nomPack;

    @Enumerated(EnumType.STRING)
    private TypePack typePack;

    @Column(name = "contenu", nullable = false)
    private String contenu;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "dateLimite", nullable = false)
    private LocalDate dateLimit;
}
