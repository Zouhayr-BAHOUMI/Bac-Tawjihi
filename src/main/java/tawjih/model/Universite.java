package tawjih.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "universites")
public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "nomUniversite", nullable = false)
    private String nomUniversite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse", referencedColumnName = "id")
    private Adresse adresse;
}
