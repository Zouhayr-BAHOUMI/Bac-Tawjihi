package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.QuestionNotFoundException;
import tawjih.model.Question;
import tawjih.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void addQuestion(Question question){

        questionRepository.save(question);
    }


    public Question getQuestion(Integer idQuestion) {

        Question question = questionRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException::new);
        return question;
    }

    public void updateQuestion(Integer idQuestion, Question question) {
        questionRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException::new);

        question.setContenuQuestion(question.getContenuQuestion());
        question.setChoix(question.getChoix());

        questionRepository.save(question);

    }

    public void deleteQuestion(Integer idQuestion){
        Question questionSupprime = questionRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException::new);

        questionRepository.delete(questionSupprime);
    }

    public List<Question> getAllQuestions() {

        return questionRepository.findAll();
    }
}
