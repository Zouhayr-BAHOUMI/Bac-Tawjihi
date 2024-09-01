package tawjih.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tawjih.model.Pack;
import tawjih.service.implimentation.PackService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-packs")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("/add")
    public ResponseEntity<String> ajouterPack(@RequestBody Pack pack){
        try {
            packService.addPack(pack);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Pack> getAllPackes() {

        return packService.getAllPackes();
    }

    @GetMapping("/idPanne")
    public Pack showsPackById(@RequestParam Integer idPack){
        Pack pack = packService.getPack(idPack);
        return pack;
    }

    @PutMapping("/update/{idPack}")
    public ResponseEntity<Void> modifierPack(@PathVariable Integer idPack, @RequestBody Pack pack){
        packService.updatePack(idPack,pack);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idPack}")
    public ResponseEntity<Void>  supprimerPack(@PathVariable Integer idPack){
        packService.deletePack(idPack);
        return ResponseEntity.noContent().build();
    }
}
