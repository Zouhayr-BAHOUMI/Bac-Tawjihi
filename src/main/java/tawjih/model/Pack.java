package tawjih.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.PackFiliere;
import tawjih.enums.StatusPack;
import tawjih.enums.TypePack;

import java.time.LocalDate;
import java.util.List;

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

    @ElementCollection(targetClass = PackFiliere.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "pack_filiere", joinColumns = @JoinColumn(name = "pack_id"))
    @Column(name = "filiere")
    private List<PackFiliere> packFilieres;


    @Enumerated(EnumType.STRING)
    private StatusPack statusPack;

    @Column(name = "contenu", nullable = false)
    private String contenu;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "dateLimite", nullable = false)
    private LocalDate dateLimit;

    @OneToMany(mappedBy = "pack")
    @JsonIgnoreProperties({"pack", "tests", "recu"})
    private List<Etudiant> etudiants;

    @OneToMany(mappedBy = "pack")
    @JsonIgnoreProperties("pack")
    private List<Recu> recus;
}
