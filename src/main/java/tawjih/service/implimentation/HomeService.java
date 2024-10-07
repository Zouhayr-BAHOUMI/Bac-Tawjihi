package tawjih.service.implimentation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tawjih.model.Pack;
import tawjih.repository.PackRepository;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private PackRepository packRepository;

    @Transactional
    public Pack getPackWithFilieres(Integer idPack) {
        return packRepository.findPackWithFilieresById(idPack);
    }

    @Transactional
    public List<Pack> getAllPacksWithFilieres() {
        return packRepository.findAllPacksWithFilieres();
    }
}
