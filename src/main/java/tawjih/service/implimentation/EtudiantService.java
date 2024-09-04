package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.EtudiantNotFoundException;
import tawjih.model.Etudiant;
import tawjih.repository.EtudiantRepository;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant getEtudiant(Integer idEtudiant) {

        Etudiant etudiant = etudiantRepository
                .findById(idEtudiant)
                .orElseThrow(EtudiantNotFoundException::new);
        return etudiant;
    }
}
