package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tawjih.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findByContenuQuestion(String contenuQuestion);

    @Query(value = "SELECT * FROM questions WHERE id_test = :idTest ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestionsByTest(@Param("idTest") Integer idTest, @Param("limit") int limit);
}
