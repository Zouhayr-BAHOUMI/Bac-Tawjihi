package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.model.Test;
import tawjih.service.implimentation.PackService;
import tawjih.service.implimentation.TestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private  PackService packService;

    @Autowired
    private TestService testService;

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
}
