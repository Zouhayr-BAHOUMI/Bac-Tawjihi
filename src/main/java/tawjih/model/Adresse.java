package tawjih.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Province;
import tawjih.enums.Region;
import tawjih.enums.Ville;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adresses")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private Province province;

    @Enumerated(EnumType.STRING)
    private Ville ville;

    @JsonIgnore
    @OneToMany(mappedBy = "adresse")
    private List<Etablissement> etablissements;

    @OneToMany(mappedBy = "adresse")
    private List<Etudiant> etudiants;
}
