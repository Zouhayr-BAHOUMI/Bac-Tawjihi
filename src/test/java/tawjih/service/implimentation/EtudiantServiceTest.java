package tawjih.service.implimentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tawjih.model.Adresse;
import tawjih.model.Etudiant;
import tawjih.repository.AdresseRepository;
import tawjih.repository.EtudiantRepository;
import tawjih.repository.PackRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private AdresseRepository adresseRepository;

    @Mock
    private PackRepository packRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEtudiant_IfEtudiantExists() {
        Integer idEtudiant = 1;
        Etudiant etudiant = new Etudiant();
        etudiant.setId(idEtudiant);

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(etudiant));

        Etudiant foundEtudiant = etudiantService.getEtudiant(idEtudiant);
        assertEquals(etudiant, foundEtudiant);
    }
}