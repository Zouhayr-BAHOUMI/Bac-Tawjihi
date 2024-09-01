package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Etablissement;

public interface EtablissementRepository extends JpaRepository<Etablissement, Integer> {
}
