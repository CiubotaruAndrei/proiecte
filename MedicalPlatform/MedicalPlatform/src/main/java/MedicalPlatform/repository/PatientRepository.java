package MedicalPlatform.repository;

import MedicalPlatform.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query
    Patient getPatientByEmail(String email);
}
