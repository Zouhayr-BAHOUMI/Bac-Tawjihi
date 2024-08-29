package tawjih.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Province;
import tawjih.enums.Region;

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
}
