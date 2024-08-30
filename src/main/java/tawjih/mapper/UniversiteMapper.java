package tawjih.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tawjih.dto.UniversiteDto;
import tawjih.model.Universite;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UniversiteMapper {

    @Mapping(target = "region", source = "adresse.region")
    @Mapping(target = "province", source = "adresse.province")
    @Mapping(target = "ville", source = "adresse.ville")
    UniversiteDto toDTO(Universite universite);

    @Mapping(target = "adresse.region", source = "region")
    @Mapping(target = "adresse.province", source = "province")
    @Mapping(target = "adresse.ville", source = "ville")
    Universite toEntity(UniversiteDto universiteDTO);

    List<UniversiteDto> toDTOList(List<Universite> universites);
    List<Universite> toEntityList(List<UniversiteDto> universiteDTOs);
}
