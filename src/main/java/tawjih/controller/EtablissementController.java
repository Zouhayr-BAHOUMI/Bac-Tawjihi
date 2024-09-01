package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.dto.UniversiteDto;
import tawjih.model.Etablissement;
import tawjih.service.implimentation.EtablissementService;

@RestController
@RequestMapping("/admin/gestion-etablissements")
public class EtablissementController {


    @Autowired
    private EtablissementService etablissementService;

    @PostMapping("/add/{idUniversite}")
    public ResponseEntity<String> ajouterEtablissement(@RequestBody Etablissement etablissement, @PathVariable Long idUniversite) {
        try {
            etablissementService.addEtablissement(etablissement,idUniversite);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successefully !!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
