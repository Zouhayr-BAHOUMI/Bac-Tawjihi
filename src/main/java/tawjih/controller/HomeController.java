package tawjih.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tawjih.dto.UniversiteDto;
import tawjih.enums.TypeEtablissement;
import tawjih.model.*;
import tawjih.service.implimentation.EtablissementService;
import tawjih.service.implimentation.PackService;
import tawjih.service.implimentation.TestService;
import tawjih.service.implimentation.UniversiteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {


    private final PackService packService;

    private final TestService testService;

    private final EtablissementService etablissementService;

    private final UniversiteService universiteService;

    @GetMapping("/packs/{idPack}")
    public ResponseEntity<Pack> getPackWithFilieres(@PathVariable Integer idPack) {
        try {
            Pack pack = packService.getPackWithFilieres(idPack);
            return ResponseEntity.ok(pack);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/packs")
    public ResponseEntity<List<Pack>> getAllPacksWithFilieres() {
        try {
            List<Pack> packs = packService.getAllPacksWithFilieres();
            return ResponseEntity.ok(packs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/test/random-test")
    public ResponseEntity<Test> getRandomTest() {
        Optional<Test> randomTest = testService.getRandomTest();
        return randomTest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/test/{idTest}/assign")
    public ResponseEntity<String> assignTestToEtudiant(@PathVariable Integer idTest, @AuthenticationPrincipal Etudiant etudiant) {
        try {
            testService.assignTestToEtudiant(idTest, etudiant.getId());
            return ResponseEntity.ok("Test assigned to Etudiant successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/etablissements")
    public List<Etablissement> getAllEtablissements() {

        return etablissementService.getAllEtablissements();
    }

    @GetMapping("/etablissement")
    public Page<Etablissement> getAllEtablissementsPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        return etablissementService.getAllEtablissementsPagination(page, size);
    }

    @GetMapping("/universities")
    public List<Universite> getAllUniversities() {

        return universiteService.getAllUniversites();
    }


    @GetMapping("/idEtablissement")
    public Etablissement showsEtablissementById(@RequestParam Integer idEtablissement){
        Etablissement etablissementFound = etablissementService.getEtablissement(idEtablissement);
        return etablissementFound;
    }

    @GetMapping("/universite/{idUniversite}")
    public List<Etablissement> getEtablissementsByUniversite(@PathVariable Long idUniversite) {
        return etablissementService.getEtablissementsByUniversite(idUniversite);
    }

    @GetMapping("/type/{typeEtablissement}")
    public ResponseEntity<List<Etablissement>> getEtablissementsByType(
            @PathVariable("typeEtablissement") TypeEtablissement typeEtablissement) {
        List<Etablissement> etablissements = etablissementService.getEtablissementsByType(typeEtablissement);
        return ResponseEntity.ok(etablissements);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Etablissement>> searchEtablissements(@RequestParam("query") String query) {
        List<Etablissement> etablissements = etablissementService.searchEtablissements(query);
        return new ResponseEntity<>(etablissements, HttpStatus.OK);
    }
}
