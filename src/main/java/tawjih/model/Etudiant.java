package tawjih.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Sexe;
import tawjih.enums.Specialite;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Etudiant extends Personne{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "cin", unique = true)
    private String cin;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "dateNaissence")
    private LocalDate dateNaissence;

    @Column(name = "lieuNaissence")
    private String lieuNaissence;

    @Column(name = "tele")
    private String tele;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    @Column(name = "codeMassar", unique = true)
    private String codeMassar;

    @ManyToOne
    @JoinColumn(name = "id_adresse")
    @JsonIgnoreProperties("etudiants")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_pack")
    @JsonIgnoreProperties("etudiants")
    private Pack pack;


    @ManyToMany
    @JoinTable(
            name = "etudiant_test",
            joinColumns = @JoinColumn(name = "id_etudiant"),
            inverseJoinColumns = @JoinColumn(name = "id_test")
    )
    @JsonIgnoreProperties("etudiants")
    private List<Test> tests;

    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("etudiants")
    private Recu recu;

}
