package tawjih.service.implimentation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tawjih.model.Pack;
import tawjih.repository.PackRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {


    private final PackRepository packRepository;

    @Transactional
    public Pack getPackWithFilieres(Integer idPack) {
        return packRepository.findPackWithFilieresById(idPack);
    }

    @Transactional
    public List<Pack> getAllPacksWithFilieres() {
        return packRepository.findAllPacksWithFilieres();
    }
}
