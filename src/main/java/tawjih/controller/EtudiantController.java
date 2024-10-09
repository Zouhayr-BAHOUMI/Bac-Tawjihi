package tawjih.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Etudiant;
import tawjih.service.implimentation.EtudiantService;
import tawjih.service.implimentation.TestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/etudiant")
@RequiredArgsConstructor
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    private final TestService testService;

    @GetMapping("/profile")
    public Etudiant showsEtudiantById(@AuthenticationPrincipal Etudiant etudiant){
       return etudiantService.getEtudiant(etudiant.getId());
    }

    @PutMapping("/compete")
    public Etudiant completeProfile(@AuthenticationPrincipal Etudiant etudiant,
                                          @RequestBody Etudiant updatedEtudiant) {

        return etudiantService.updateEtudiant(etudiant.getId(), updatedEtudiant);

    }

    @PostMapping("/choisir-pack/{idPack}")
    public ResponseEntity<Map<String, Object>> selectPack(@AuthenticationPrincipal Etudiant etudiant,
                                               @PathVariable Integer idPack) {

        Etudiant etudiantLogged = etudiantService.choisirPack(etudiant.getId(), idPack);

        Map<String, Object> response = new HashMap<>();
        response.put("etudiant", etudiantLogged);
        response.put("pack", etudiantLogged.getPack());

        return ResponseEntity.ok(response);

    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitTestAnswers(@RequestBody List<Integer> idChoixChoisi) {
        String recommendation = testService.evaluateTest(idChoixChoisi);
        return ResponseEntity.ok(recommendation);
    }
}
