package tawjih.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Question;
import tawjih.service.implimentation.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-questions")
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<?> ajouterQuestion( @RequestParam Integer idTest ,@RequestBody Question question){
        try {
            questionService.addQuestion(question, idTest);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/idPanne")
    public Question showsQuestionById(@RequestParam Integer idQuestion){
        Question question = questionService.getQuestion(idQuestion);
        return question;
    }

    @PutMapping("/update/{idQuestion}")
    public ResponseEntity<Void> modifierQuestion(@PathVariable Integer idQuestion, @RequestBody Question question){
        questionService.updateQuestion(idQuestion,question);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idQuestion}")
    public ResponseEntity<Void>  supprimerQuestion(@PathVariable Integer idQuestion){
        questionService.deleteQuestion(idQuestion);
        return ResponseEntity.noContent().build();
    }

}
