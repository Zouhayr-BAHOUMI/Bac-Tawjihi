package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Universite;
import tawjih.service.implimentation.UniversiteService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-universities")
public class UniversiteController {

    @Autowired
    private UniversiteService universiteService;

    @PostMapping("/add")
    public ResponseEntity<String> ajouterUniversite(@RequestBody Universite universite){
        try {
            universiteService.addUniversite(universite);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Universite> getAllUniversites() {

        return universiteService.getAllUniversites();
    }

    @GetMapping("/idUniversite")
    public Universite showsUniversiteById(@RequestParam Long idUniversite){
        Universite universite = universiteService.getUniversite(idUniversite);
        return universite;
    }

    @PutMapping("/update/{idUniversite}")
    public ResponseEntity<Void> modifierUniversite(@PathVariable Long idUniversite, @RequestBody Universite universite){
        universiteService.updateUniversite(idUniversite,universite);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idUniversite}")
    public ResponseEntity<Void>  supprimerUniversite(@PathVariable Long idUniversite){
        universiteService.deleteUniversite(idUniversite);
        return ResponseEntity.noContent().build();
    }

}
