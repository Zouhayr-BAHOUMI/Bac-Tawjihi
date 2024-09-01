package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.dto.UniversiteDto;
import tawjih.exception.UniversiteNotFoundException;
import tawjih.mapper.UniversiteMapper;
import tawjih.model.Adresse;
import tawjih.model.Universite;
import tawjih.repository.UniversiteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UniversiteService {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private UniversiteMapper universiteMapper;

    public UniversiteDto addUniversite(UniversiteDto universiteDTO) {
        Universite universite = universiteMapper.toEntity(universiteDTO);
        Universite savedUniversite = universiteRepository.save(universite);
        return universiteMapper.toDTO(savedUniversite);
    }

    public List<Universite> getAllUniversites() {

        return universiteRepository.findAll();
    }

    public Universite getUniversite(Long idUniversite) {

        Universite universite = universiteRepository
                .findById(idUniversite)
                .orElseThrow(UniversiteNotFoundException::new);
        return universite;
    }

    public void updateUniversite(Long idUniversite, Universite universiteUpdate) {
        Universite existUniversite = universiteRepository
                .findById(idUniversite)
                .orElseThrow(UniversiteNotFoundException::new);

        existUniversite.setNomUniversite(universiteUpdate.getNomUniversite());
        existUniversite.setImageUrl(universiteUpdate.getImageUrl());

        Adresse existAdresse = existUniversite.getAdresse();
        Adresse newAdresse = universiteUpdate.getAdresse();

        if (existAdresse != null && newAdresse != null) {
            existAdresse.setRegion(newAdresse.getRegion());
            existAdresse.setProvince(newAdresse.getProvince());
            existAdresse.setVille(newAdresse.getVille());
        } else if (newAdresse != null) {
            existUniversite.setAdresse(newAdresse);
        }

        universiteRepository.save(existUniversite);
    }

    public void deleteUniversite(Long idUniversite){
        Universite universiteSupprime = universiteRepository
                .findById(idUniversite)
                .orElseThrow(UniversiteNotFoundException::new);

        universiteRepository.delete(universiteSupprime);
    }
}
