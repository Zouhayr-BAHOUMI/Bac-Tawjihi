package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.service.implimentation.EtudiantService;

@RestController
@RequestMapping("/etudiant/profile")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping
    public Etudiant showsEtudiantById(@AuthenticationPrincipal Etudiant etudiant){
       return etudiantService.getEtudiant(etudiant.getId());
    }

    @PutMapping("/compete")
    public Etudiant completeProfile(@AuthenticationPrincipal Etudiant etudiant,
                                          @RequestBody Etudiant updatedEtudiant) {

        return etudiantService.updateEtudiant(etudiant.getId(), updatedEtudiant);

    }
}
