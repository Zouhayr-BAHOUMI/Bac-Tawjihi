package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.TestNotFoundException;
import tawjih.model.Etudiant;
import tawjih.model.Test;
import tawjih.repository.EtudiantRepository;
import tawjih.repository.TestRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

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
}
