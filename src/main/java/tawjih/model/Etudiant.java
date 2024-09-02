package tawjih.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Sexe;
import tawjih.enums.Specialite;
import tawjih.enums.TypePack;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Etudiant extends Personne{

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "cin", nullable = false, unique = true)
    private String cin;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "dateNaissence")
    private LocalDate dateNaissence;

    @Column(name = "lieuNaissence", nullable = false)
    private String lieuNaissence;

    @Column(name = "tele", nullable = false)
    private String tele;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    @Column(name = "codeMassar", nullable = false, unique = true)
    private String codeMassar;
}
