package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.service.implimentation.EtudiantService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

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
}
