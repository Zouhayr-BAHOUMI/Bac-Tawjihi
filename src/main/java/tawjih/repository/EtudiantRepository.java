package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
}
