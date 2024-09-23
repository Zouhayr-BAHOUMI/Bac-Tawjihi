package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.QuestionNotFoundException;
import tawjih.exception.TestNotFoundException;
import tawjih.model.Choix;
import tawjih.model.Question;
import tawjih.model.Test;
import tawjih.repository.QuestionRepository;
import tawjih.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TestRepository testRepository;

    public void addQuestion(Question question, Integer idTest){

        Test test = testRepository
                .findById(idTest).orElseThrow(TestNotFoundException::new);

        validationInputs(question);
        Optional<Question> existQuestion = questionRepository
                .findByContenuQuestion(question.getContenuQuestion());

        if (existQuestion.isPresent()){
            throw new IllegalArgumentException("contenu deja existe.");
        }

        long correctChoice = question.getChoix()
                .stream().filter(Choix::isCorrect).count();

        if (correctChoice != 1) {
            throw new IllegalArgumentException("choose at least one correct");
        }

        question.setTest(test);
        questionRepository.save(question);
    }


    public Question getQuestion(Integer idQuestion) {

        Question question = questionRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException::new);
        return question;
    }

    public void updateQuestion(Integer idQuestion, Question question) {

        validationInputs(question);
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

    private void validationInputs(Question question){
        if(question.getContenuQuestion() == null && question.getContenuQuestion().isEmpty()){
            throw new IllegalArgumentException("Question content is null or empty");
        }
        if (question.getChoix() == null && question.getChoix().isEmpty()){
            throw new IllegalArgumentException("Question choix is null or empty");
        }
    }
}
