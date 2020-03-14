package MedicalPlatform.repository;

import MedicalPlatform.model.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CaregiverRepository extends JpaRepository<Caregiver, Integer> {

    @Query
    Caregiver getCaregiverByEmail(String email);
}
