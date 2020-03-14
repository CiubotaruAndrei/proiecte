package MedicalPlatform.repository;

import MedicalPlatform.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    @Query
    Medication getMedicationByName(String name);
}
