package tawjih.service.implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.UniversiteNotFoundException;
import tawjih.model.Adresse;
import tawjih.model.Etablissement;
import tawjih.model.Universite;
import tawjih.repository.AdresseRepository;
import tawjih.repository.EtablissementRepository;
import tawjih.repository.UniversiteRepository;

@Service
public class EtablissementService {

    @Autowired
    private EtablissementRepository etablissementRepository;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    public Etablissement addEtablissement(Etablissement etablissement, Long idUniversite){
         Universite universite = universiteRepository
                .findById(idUniversite)
                .orElseThrow(UniversiteNotFoundException::new);


        Adresse universiteAdresse = universite.getAdresse();
        if (universiteAdresse == null) {
            throw new RuntimeException("University address not found");
        }

        if (universiteAdresse.getVille() == null && etablissement.getAdresse() != null) {
            universiteAdresse.setVille(etablissement.getAdresse().getVille());
            adresseRepository.save(universiteAdresse);
        }


        etablissement.setAdresse(universiteAdresse);
        etablissement.setUniversite(universite);

        return etablissementRepository.save(etablissement);
    }
}
