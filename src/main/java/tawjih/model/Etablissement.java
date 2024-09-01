package tawjih.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.TypeEtablissement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etablissements")
public class Etablissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomEtablissement", nullable = false)
    private String nomEtablissement;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "proceduresCandidature")
    private String proceduresCandidature;

    @Column(name = "calendrier")
    private String calendrier;

    @Enumerated(EnumType.STRING)
    private TypeEtablissement typeEtablissement;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_universite")
    private Universite universite;

    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;


}
