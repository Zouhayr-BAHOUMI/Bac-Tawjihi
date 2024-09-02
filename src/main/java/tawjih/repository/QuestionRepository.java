package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findByContenuQuestion(String contenuQuestion);
}
