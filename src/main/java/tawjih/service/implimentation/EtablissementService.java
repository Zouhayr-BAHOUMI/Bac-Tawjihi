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
            throw new UniversiteNotFoundException();
        }

        if (universiteAdresse.getRegion() != null) {
            etablissement.getAdresse().setRegion(universiteAdresse.getRegion());
        } else {
            throw new UniversiteNotFoundException();
        }

        if (etablissement.getAdresse().getVille() == null) {
            etablissement.getAdresse().setVille(universiteAdresse.getVille());
        }
        if (etablissement.getAdresse().getProvince() == null) {
            etablissement.getAdresse().setProvince(universiteAdresse.getProvince());
        }


        adresseRepository.save(etablissement.getAdresse());

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

    public List<Etablissement> getEtablissementsByUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(UniversiteNotFoundException::new);
        return etablissementRepository.findByUniversite(universite);
    }


}
