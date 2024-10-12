package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.enums.TypeEtablissement;
import tawjih.model.Etablissement;
import tawjih.model.Universite;

import java.util.List;

public interface EtablissementRepository extends JpaRepository<Etablissement, Integer> {
    List<Etablissement> findByUniversite(Universite universite);

    List<Etablissement> findByTypeEtablissement(TypeEtablissement type);
}
