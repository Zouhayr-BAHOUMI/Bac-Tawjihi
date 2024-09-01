package tawjih.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tawjih.dto.AdresseDto;
import tawjih.dto.UniversiteDto;
import tawjih.model.Adresse;
import tawjih.model.Universite;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UniversiteMapper {

    @Mappings({
            @Mapping(source = "universite.adresse", target = "adresse")
    })
    UniversiteDto toDTO(Universite universite);

    @Mappings({
            @Mapping(source = "universiteDTO.adresse", target = "adresse")
    })
    Universite toEntity(UniversiteDto universiteDTO);

    AdresseDto adresseToDTO(Adresse adresse);

    Adresse dtoToAdresse(AdresseDto adresseDTO);

    List<UniversiteDto> toDTOList(List<Universite> universites);
    List<Universite> toEntityList(List<UniversiteDto> universiteDTOs);
}
