package tawjih.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tawjih.model.Question;
import tawjih.service.implimentation.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
@RequiredArgsConstructor
public class RecommandationController {


    private final QuestionService questionService;

    @GetMapping("/tenRandom-questions")
    public ResponseEntity<List<Question>> getRandomTenQuestions() {
        List<Question> randomQuestions = questionService.getRandomTenQuestions();
        return ResponseEntity.ok(randomQuestions);
    }
}
