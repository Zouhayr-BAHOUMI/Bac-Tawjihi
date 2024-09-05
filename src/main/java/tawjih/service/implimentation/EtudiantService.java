package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.EtudiantNotFoundException;
import tawjih.model.Adresse;
import tawjih.model.Etudiant;
import tawjih.repository.AdresseRepository;
import tawjih.repository.EtudiantRepository;

import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private AdresseRepository adresseRepository;

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
        etudiant.setCin(updateEtudiant.getCin());
        etudiant.setSexe(updateEtudiant.getSexe());
        etudiant.setTele(updateEtudiant.getTele());
        etudiant.setDateNaissence(updateEtudiant.getDateNaissence());
        etudiant.setLieuNaissence(updateEtudiant.getLieuNaissence());
        etudiant.setCodeMassar(updateEtudiant.getCodeMassar());
        etudiant.setSpecialite(updateEtudiant.getSpecialite());


        Adresse newAdresse = updateEtudiant.getAdresse();
        if (newAdresse!= null){
            Optional<Adresse> existeAdresse = adresseRepository
                    .findByRegionAndAndProvinceAndAndVille(
                            newAdresse.getRegion(),
                            newAdresse.getProvince(),
                            newAdresse.getVille()
                    );

            if (existeAdresse.isPresent()){
                etudiant.setAdresse(existeAdresse.get());
            }else {
                etudiant.setAdresse(newAdresse);
            }
        }

        return etudiantRepository.save(etudiant);
    }
}
