package tawjih.service.implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.EtablissementNotFoundException;
import tawjih.exception.UniversiteNotFoundException;
import tawjih.model.Adresse;
import tawjih.model.Etablissement;
import tawjih.model.Universite;
import tawjih.repository.AdresseRepository;
import tawjih.repository.EtablissementRepository;
import tawjih.repository.UniversiteRepository;

import java.util.List;

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

    public List<Etablissement> getAllEtablissements() {

        return etablissementRepository.findAll();
    }

    public Etablissement getEtablissement(Integer idEtablissement) {

        Etablissement etablissement = etablissementRepository
                .findById(idEtablissement)
                .orElseThrow(EtablissementNotFoundException::new);
        return etablissement;
    }

    public void updateEtablissement(Integer idEtablissement, Etablissement etablissementUpdate) {
        Etablissement existEtablissement = etablissementRepository
                .findById(idEtablissement)
                .orElseThrow(EtablissementNotFoundException::new);

        existEtablissement.setNomEtablissement(etablissementUpdate.getNomEtablissement());
        existEtablissement.setLocalisation(etablissementUpdate.getLocalisation());
        existEtablissement.setCondition(etablissementUpdate.getCondition());
        existEtablissement.setProceduresCandidature(etablissementUpdate.getProceduresCandidature());
        existEtablissement.setCalendrier(etablissementUpdate.getCalendrier());
        existEtablissement.setTypeEtablissement(etablissementUpdate.getTypeEtablissement());

        if (etablissementUpdate.getAdresse() != null && etablissementUpdate.getAdresse().getVille() != null) {
            Adresse existingAdresse = existEtablissement.getAdresse();
            existingAdresse.setVille(etablissementUpdate.getAdresse().getVille());
            adresseRepository.save(existingAdresse);
        }

        etablissementRepository.save(existEtablissement);
    }

    public void deleteEtablissement(Integer idEtablissement){
        Etablissement etablissementSupprime = etablissementRepository
                .findById(idEtablissement)
                .orElseThrow(EtablissementNotFoundException::new);

        etablissementRepository.delete(etablissementSupprime);
    }


}
