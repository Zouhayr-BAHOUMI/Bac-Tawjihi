package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Question;
import tawjih.model.Test;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {
    Optional<Test> findByTitre(String titre);
}
