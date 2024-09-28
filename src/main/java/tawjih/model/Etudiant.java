package tawjih.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Sexe;
import tawjih.enums.Specialite;
import tawjih.enums.TypePack;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_pack")
    private Pack pack;


    @OneToMany(mappedBy = "etudiant")
    private List<Test> tests;

    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private Recu recu;

}
