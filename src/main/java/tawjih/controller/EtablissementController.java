package tawjih.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Etablissement;
import tawjih.service.implimentation.EtablissementService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-etablissements")
@RequiredArgsConstructor
public class EtablissementController {

    private final EtablissementService etablissementService;

    @PostMapping("/add/{idUniversite}")
    public ResponseEntity<String> ajouterEtablissement(@RequestBody Etablissement etablissement, @PathVariable Long idUniversite) {
        try {
            etablissementService.addEtablissement(etablissement,idUniversite);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successefully !!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public List<Etablissement> getAllEtablissements() {

        return etablissementService.getAllEtablissements();
    }

    @GetMapping("/idEtablissement")
    public Etablissement showsEtablissementById(@RequestParam Integer idEtablissement){
        Etablissement etablissementFound = etablissementService.getEtablissement(idEtablissement);
        return etablissementFound;
    }

    @PutMapping("/update/{idEtablissement}")
    public ResponseEntity<Void> modifierEtablissement(@PathVariable Integer idEtablissement, @RequestBody Etablissement etablissement){
        etablissementService.updateEtablissement(idEtablissement,etablissement);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idEtablissement}")
    public ResponseEntity<Void>  supprimerEtablissement(@PathVariable Integer idEtablissement){
        etablissementService.deleteEtablissement(idEtablissement);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/universite/{idUniversite}")
    public List<Etablissement> getEtablissementsByUniversite(@PathVariable Long idUniversite) {
        return etablissementService.getEtablissementsByUniversite(idUniversite);
    }
}
