package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tawjih.model.Pack;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Integer> {
    @Query(value = "SELECT p.*, pf.filiere FROM packs p " +
            "JOIN pack_filiere pf ON p.id = pf.pack_id " +
            "WHERE p.id = :id", nativeQuery = true)
    Pack findPackWithFilieresById(Integer id);

    @Query("SELECT DISTINCT p FROM Pack p JOIN FETCH p.packFilieres")
    List<Pack> findAllPacksWithFilieres();
}
