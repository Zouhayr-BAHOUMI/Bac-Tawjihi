package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.model.Recu;

public interface RecuRepository extends JpaRepository<Recu, Integer> {
    boolean existsByEtudiantAndPack(Etudiant etudiant, Pack pack);
}
