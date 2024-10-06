package tawjih.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tawjih.model.Pack;
import tawjih.service.implimentation.PackService;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private  PackService packService;

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
}
