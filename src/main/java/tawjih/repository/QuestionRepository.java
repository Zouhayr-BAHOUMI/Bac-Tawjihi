package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
