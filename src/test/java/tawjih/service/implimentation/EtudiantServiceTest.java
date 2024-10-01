package tawjih.service.implimentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tawjih.enums.StatusPack;
import tawjih.exception.EtudiantNotFoundException;
import tawjih.model.Adresse;
import tawjih.model.Etudiant;
import tawjih.model.Pack;
import tawjih.repository.AdresseRepository;
import tawjih.repository.EtudiantRepository;
import tawjih.repository.PackRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private PackRepository packRepository;

    @Mock
    private AdresseRepository adresseRepository;

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

    @Test
    public void testGetEtudiant_IfEtudiantNotExist() {
        Integer idEtudiant = 1;

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.empty());

        assertThrows(EtudiantNotFoundException.class, () -> {
            etudiantService.getEtudiant(idEtudiant);
        });
    }

    @Test
    public void testUpdateEtudiant_WhenEtudiantExists() {
        Integer idEtudiant = 1;
        Etudiant existingEtudiant = new Etudiant();
        existingEtudiant.setId(idEtudiant);
        existingEtudiant.setNom("Old Name");

        Etudiant updatedEtudiant = new Etudiant();
        updatedEtudiant.setNom("New Name");
        updatedEtudiant.setPrenom("New Prenom");
        Adresse newAdresse = new Adresse();
        newAdresse.setRegion(tawjih.enums.Region.RABAT_SALE_KENITRA);
        newAdresse.setProvince(tawjih.enums.Province.KENITRA);
        newAdresse.setVille(tawjih.enums.Ville.KENITRA);
        updatedEtudiant.setAdresse(newAdresse);

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(existingEtudiant));
        when(adresseRepository.findByRegionAndAndProvinceAndAndVille(any(), any(), any())).thenReturn(Optional.empty());
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(updatedEtudiant);

        Etudiant result = etudiantService.updateEtudiant(idEtudiant, updatedEtudiant);

        assertEquals("New Name", result.getNom());
        assertEquals("New Prenom", result.getPrenom());
        assertEquals(newAdresse, result.getAdresse());
        verify(etudiantRepository).save(any(Etudiant.class));
    }

    @Test
    public void testUpdateEtudiant_WhenAdresseExists() {
        Integer idEtudiant = 1;
        Etudiant existingEtudiant = new Etudiant();
        existingEtudiant.setId(idEtudiant);

        Etudiant updatedEtudiant = new Etudiant();
        Adresse existingAdresse = new Adresse();
        existingAdresse.setRegion(tawjih.enums.Region.RABAT_SALE_KENITRA);
        updatedEtudiant.setAdresse(existingAdresse);

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(existingEtudiant));
        when(adresseRepository.findByRegionAndAndProvinceAndAndVille(any(), any(), any())).thenReturn(Optional.of(existingAdresse));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(updatedEtudiant);

        Etudiant result = etudiantService.updateEtudiant(idEtudiant, updatedEtudiant);

        assertEquals(existingAdresse, result.getAdresse());
        verify(adresseRepository).save(existingAdresse);
    }

    @Test
    public void testChoisirPack_WhenEtudiantAndPackExist() {
        Integer idEtudiant = 1;
        Integer idPack = 1;

        Etudiant etudiant = new Etudiant();
        etudiant.setId(idEtudiant);

        Pack pack = new Pack();
        pack.setId(idPack);

        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(etudiant));
        when(packRepository.findById(idPack)).thenReturn(Optional.of(pack));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.choisirPack(idEtudiant, idPack);

        assertNotNull(updatedEtudiant);
        assertEquals(pack, updatedEtudiant.getPack());
        assertEquals(StatusPack.IMPAYE, pack.getStatusPack());
        verify(packRepository).save(pack);
        verify(etudiantRepository).save(etudiant);
    }
}