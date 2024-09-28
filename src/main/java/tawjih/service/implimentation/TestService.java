package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.TestNotFoundException;
import tawjih.model.Test;
import tawjih.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

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

    public void updateTest(Integer idTest, Test test) {


        testRepository
                .findById(idTest)
                .orElseThrow(TestNotFoundException::new);

        test.setTitre(test.getTitre());
        test.setDescription(test.getDescription());
        test.setDuree(test.getDuree());

        testRepository.save(test);

    }

    public void deleteTest(Integer idTest){
        Test testSupprime = testRepository
                .findById(idTest)
                .orElseThrow(TestNotFoundException::new);

        testRepository.delete(testSupprime);
    }
}
