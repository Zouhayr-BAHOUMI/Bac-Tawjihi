package tawjih.service.implimentation;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.enums.Domain;
import tawjih.exception.ChoixNotFoundException;
import tawjih.exception.TestNotFoundException;
import tawjih.model.Choix;
import tawjih.model.Etudiant;
import tawjih.model.Question;
import tawjih.model.Test;
import tawjih.repository.ChoixRepository;
import tawjih.repository.EtudiantRepository;
import tawjih.repository.TestRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TestService {

    @Autowired
    private TestRepository testRepository;

    private final ChoixRepository choixRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public void addTest(Test test){

        Optional<Test> existTest = testRepository
                .findByTitre(test.getTitre());

        if (existTest.isPresent()){
            throw new IllegalArgumentException("titre deja existe.");
        }
        testRepository.save(test);
    }


    public List<Test> getAllTests() {

        return testRepository.findAll();
    }

    public Test getTest(Integer idTest) {

        return testRepository
                .findById(idTest)
                .orElseThrow(TestNotFoundException::new);

    }

    public Optional<Test> getRandomTest() {
        List<Test> allTests = getAllTests();
        if (allTests.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        int randomIndex = random.nextInt(allTests.size());
        return Optional.of(allTests.get(randomIndex));
    }

    public Test assignTestToEtudiant(Integer idTest, Integer idEtudiant) {

        Optional<Test> testOptional = testRepository.findById(idTest);
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);

        if (testOptional.isPresent() && etudiantOptional.isPresent()) {
            Test test = testOptional.get();
            Etudiant etudiant = etudiantOptional.get();

            if (etudiant.getTests().contains(test)) {
                return test;
            }

            etudiant.getTests().add(test);
            test.getEtudiants().add(etudiant);

            etudiantRepository.save(etudiant);
            testRepository.save(test);

            return test;
        } else {
            throw new RuntimeException("Test or Etudiant not found");
        }
    }

    public void updateTest(Integer idTest, Test test) {


        Test existTest = testRepository
                .findById(idTest)
                .orElseThrow(TestNotFoundException::new);

        existTest.setTitre(test.getTitre());
        existTest.setDescription(test.getDescription());
        existTest.setDuree(test.getDuree());

        testRepository.save(existTest);

    }

    public void deleteTest(Integer idTest){
        Test testSupprime = testRepository
                .findById(idTest)
                .orElseThrow(TestNotFoundException::new);

        testRepository.delete(testSupprime);
    }

    public String evaluateTest(List<Integer> idChoixChoisi) {

        Map<Domain, Integer> correctReponse = new HashMap<>();
        int totalCorrectChoisi = 0;

        for (Integer idChoix : idChoixChoisi) {

            Choix selectChoix = choixRepository.findById(idChoix)
                    .orElseThrow(ChoixNotFoundException::new);

            Question question = selectChoix.getQuestion();

            if(selectChoix.isCorrect()){
                totalCorrectChoisi ++;
                Domain domain = question.getDomain();
                correctReponse .put(domain, correctReponse.getOrDefault(domain, 0) + 1);
            }
        }

        Domain recommendedDomain = correctReponse
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return  recommendedDomain != null ? "Recommended Domain: " + recommendedDomain : "No recommendation available.";
    }
}
