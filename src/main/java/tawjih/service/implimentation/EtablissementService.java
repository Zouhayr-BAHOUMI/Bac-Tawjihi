package tawjih.service.implimentation;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EtablissementService {


    private final EtablissementRepository etablissementRepository;

    private final UniversiteRepository universiteRepository;

    private final AdresseRepository adresseRepository;

    public Etablissement addEtablissement(Etablissement etablissement, Long idUniversite){
        Universite universite = universiteRepository
                .findById(idUniversite)
                .orElseThrow(UniversiteNotFoundException::new);
        Adresse universiteAdresse = universite.getAdresse();
        if (universiteAdresse == null || universiteAdresse.getRegion() == null) {
            throw new UniversiteNotFoundException();
        }

        etablissement.getAdresse().setRegion(universiteAdresse.getRegion());

        Adresse adresseToUse = null;

        Adresse exactMatch = adresseRepository.findByRegionAndProvinceAndVille(
                etablissement.getAdresse().getRegion(),
                etablissement.getAdresse().getProvince(),
                etablissement.getAdresse().getVille()

        );
        if (exactMatch != null) {
            adresseToUse = exactMatch;
        } else {

            List<Adresse> partialMatches = adresseRepository.findByRegion(etablissement.getAdresse().getRegion());
            for (Adresse partialMatch : partialMatches) {
                if ((partialMatch.getProvince() == null && etablissement.getAdresse().getProvince() != null) ||
                        (partialMatch.getVille() == null && etablissement.getAdresse().getVille() != null)) {

                    if (partialMatch.getProvince() == null) {
                        partialMatch.setProvince(etablissement.getAdresse().getProvince());
                    }
                    if (partialMatch.getVille() == null) {
                        partialMatch.setVille(etablissement.getAdresse().getVille());
                    }
                    adresseToUse = adresseRepository.save(partialMatch);
                    break;
                }
            }
        }

        // If no suitable address found, create a new one
        if (adresseToUse == null) {
            adresseToUse = adresseRepository.save(etablissement.getAdresse());
        }

        etablissement.setAdresse(adresseToUse);
        etablissement.setUniversite(universite);
        return etablissementRepository.save(etablissement);

//        Adresse etablissementAdresse = etablissement.getAdresse();
//
//
//        Adresse existingAdresse = adresseRepository.findByRegionAndProvinceAndVille(
//                etablissementAdresse.getRegion(),
//                etablissementAdresse.getProvince(),
//                etablissementAdresse.getVille());
//
//        if (existingAdresse == null) {
//
//            existingAdresse = new Adresse();
//            existingAdresse.setRegion(etablissementAdresse.getRegion());
//            existingAdresse.setProvince(etablissementAdresse.getProvince());
//            existingAdresse.setVille(etablissementAdresse.getVille());
//            adresseRepository.save(existingAdresse);
//        }
//
//        etablissement.setAdresse(existingAdresse);
//        etablissement.setUniversite(universite);
//
//        return etablissementRepository.save(etablissement);
//        Adresse universiteAdresse = universite.getAdresse();
//        if (universiteAdresse == null) {
//            throw new RuntimeException("University address not found");
//        }
//
//        if (universiteAdresse.getVille() == null && etablissement.getAdresse() != null) {
//            universiteAdresse.setVille(etablissement.getAdresse().getVille());
//        }
//
//        if (universiteAdresse.getProvince() == null && etablissement.getAdresse() != null) {
//            universiteAdresse.setProvince(etablissement.getAdresse().getProvince());
//        }

//        adresseRepository.save(universiteAdresse);
//
//        etablissement.setAdresse(universiteAdresse);
//        etablissement.setUniversite(universite);
//
//        return etablissementRepository.save(etablissement);
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
