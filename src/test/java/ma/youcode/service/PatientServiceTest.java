package ma.youcode.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import ma.youcode.dao.PatientDAO;
import ma.youcode.model.Patient;

@DisplayName("tset")
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private PatientDAO patientDAO;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setNom("ismail");
        patient.setPrenom("ismail");

    }

    @Test
    @DisplayName(" l ajout dans database")
    void test_ajout_dans_dataabse() {
        when(patientDAO.save(patient)).thenReturn(patient);
    
        Patient newPatient = patientService.create(patient);

        assertNotNull(newPatient, "   operation failed");
        assertEquals("ismail", newPatient.getNom());
    }

    @Test
    @Disabled
    @DisplayName("test methode get patient")
    void test_get_patient() {
        when(patientDAO.findById(anyLong())).thenReturn(Optional.of(patient));
        Optional<Patient> newPAtient = patientService.findById(1L);
        assertTrue(newPAtient.isPresent(), " object null");

    }

    
}