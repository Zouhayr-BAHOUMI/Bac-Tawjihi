package tawjih.service.implimentation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tawjih.enums.StatusPack;
import tawjih.exception.PackNotFoundException;
import tawjih.model.Pack;
import tawjih.repository.PackRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackService {

    private final PackRepository packRepository;

    public void addPack(Pack pack){
        System.out.println(pack);
        pack.setStatusPack(StatusPack.IMPAYE);
        packRepository.save(pack);
    }


    public Pack getPack(Integer idPack) {

        return  packRepository
                .findById(idPack)
                .orElseThrow(PackNotFoundException::new);

    }

    public void updatePack(Integer idPack, Pack pack) {
        packRepository
                .findById(idPack)
                .orElseThrow(PackNotFoundException::new);

        pack.setId(idPack);

        packRepository.save(pack);

    }

    public void deletePack(Integer idPack){
        Pack packSupprime = packRepository
                .findById(idPack)
                .orElseThrow(PackNotFoundException::new);

        packRepository.delete(packSupprime);
    }

    public List<Pack> getAllPackes() {

        return packRepository.findAll();
    }

    public Pack getPackWithFilieres(Integer idPack) {
        return packRepository.findPackWithFilieresById(idPack);
    }

    public List<Pack> getAllPacksWithFilieres() {
        return packRepository.findAllPacksWithFilieres();
    }


}
