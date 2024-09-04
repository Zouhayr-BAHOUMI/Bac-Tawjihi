package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.service.implimentation.EtudiantService;

@RestController
@RequestMapping("/etudiant/update-profile")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/idEtudiant")
    public Etudiant showsEtudiantById(@RequestParam Integer idEtudiant){
        Etudiant etudiant = etudiantService.getEtudiant(idEtudiant);
        return etudiant;
    }
}
