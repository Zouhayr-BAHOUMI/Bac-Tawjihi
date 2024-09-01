package tawjih.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.exception.PackNotFoundException;
import tawjih.model.Pack;
import tawjih.repository.PackRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    public void addPack(Pack pack){

        packRepository.save(pack);
    }


    public Pack getPack(Integer idPack) {

        Pack pack = packRepository
                .findById(idPack)
                .orElseThrow(PackNotFoundException::new);
        return pack;
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
}
