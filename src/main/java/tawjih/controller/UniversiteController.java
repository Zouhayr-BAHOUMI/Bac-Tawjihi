package tawjih.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.dto.UniversiteDto;
import tawjih.model.Universite;
import tawjih.service.implimentation.UniversiteService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-universities")
@RequiredArgsConstructor
public class UniversiteController {

    private final UniversiteService universiteService;

    @PostMapping("/add")
    public ResponseEntity<UniversiteDto> ajouterUniversite(@RequestBody UniversiteDto universiteDTO) {
        try {
            UniversiteDto createdUniversite = universiteService.addUniversite(universiteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUniversite);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
