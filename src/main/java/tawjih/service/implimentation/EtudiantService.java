package tawjih.service.implimentation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tawjih.enums.StatusPack;
import tawjih.exception.EtudiantNotFoundException;
import tawjih.exception.PackNotFoundException;
import tawjih.model.Adresse;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.repository.AdresseRepository;
import tawjih.repository.EtudiantRepository;
import tawjih.repository.PackRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    private final AdresseRepository adresseRepository;

    private final PackRepository packRepository;

    public Etudiant getEtudiant(Integer idEtudiant) {

        Etudiant etudiant = etudiantRepository
                .findById(idEtudiant)
                .orElseThrow(EtudiantNotFoundException::new);
        return etudiant;
    }

    public Etudiant updateEtudiant(Integer idEtudiant, Etudiant updateEtudiant) {
        Etudiant etudiant = getEtudiant(idEtudiant);

        etudiant.setNom(updateEtudiant.getNom());
        etudiant.setPrenom(updateEtudiant.getPrenom());
        etudiant.setImageUrl(updateEtudiant.getImageUrl());
        etudiant.setCin(updateEtudiant.getCin());
        etudiant.setSexe(updateEtudiant.getSexe());
        etudiant.setTele(updateEtudiant.getTele());
        etudiant.setDateNaissence(updateEtudiant.getDateNaissence());
        etudiant.setLieuNaissence(updateEtudiant.getLieuNaissence());
        etudiant.setCodeMassar(updateEtudiant.getCodeMassar());
        etudiant.setSpecialite(updateEtudiant.getSpecialite());


        Adresse newAdresse = updateEtudiant.getAdresse();
        if (newAdresse!= null){
            Optional<Adresse> existeAdresse = Optional.ofNullable(adresseRepository
                    .findByRegionAndProvinceAndVille(
                            newAdresse.getRegion(),
                            newAdresse.getProvince(),
                            newAdresse.getVille()
                    ));

            if (existeAdresse.isPresent()){
                Adresse adresse = existeAdresse.get();
                if (adresse.getRegion() == null && newAdresse.getRegion() != null) {
                    adresse.setRegion(newAdresse.getRegion());
                }
                if (adresse.getProvince() == null && newAdresse.getProvince() != null) {
                    adresse.setProvince(newAdresse.getProvince());
                }
                if (adresse.getVille() == null && newAdresse.getVille() != null) {
                    adresse.setVille(newAdresse.getVille());
                }

                adresseRepository.save(adresse);
                etudiant.setAdresse(adresse);

            }else {
                adresseRepository.save(newAdresse);
                etudiant.setAdresse(newAdresse);
            }
        }

        return etudiantRepository.save(etudiant);
    }

    public Etudiant choisirPack(Integer idEtudiant, Integer idPack){
        Etudiant etudiant = getEtudiant(idEtudiant);
        Pack packChoisi = packRepository
                .findById(idPack)
                .orElseThrow(PackNotFoundException::new);

        etudiant.setPack(packChoisi);

        packChoisi.setStatusPack(StatusPack.IMPAYE);

        packRepository.save(packChoisi);

        return etudiantRepository.save(etudiant);
    }
}
