package tawjih.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Province;
import tawjih.enums.Region;
import tawjih.enums.Ville;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversiteDto {

    private Long id;
    private String imageUrl;
    private String nomUniversite;
    private Region region;
    private Province province;
    private Ville ville;
}
