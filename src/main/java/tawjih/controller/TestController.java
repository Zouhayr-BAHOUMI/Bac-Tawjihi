package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Test;
import tawjih.service.implimentation.TestService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-tests")
public class TestController {

    @Autowired
    private TestService testService;


    @PostMapping("/add")
    public ResponseEntity<String> ajouterTest(@RequestBody Test test){
        try {
            testService.addTest(test);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/idTest")
    public Test showsTestById(@RequestParam Integer idTest){
        return testService.getTest(idTest);
    }

    @PutMapping("/update/{idTest}")
    public ResponseEntity<Void> modifierTest(@PathVariable Integer idTest, @RequestBody Test test){
        testService.updateTest(idTest,test);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idTest}")
    public ResponseEntity<Void>  supprimerTest(@PathVariable Integer idTest){
        testService.deleteTest(idTest);
        return ResponseEntity.noContent().build();
    }
}
