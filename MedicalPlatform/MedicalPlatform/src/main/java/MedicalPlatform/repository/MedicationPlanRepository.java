package MedicalPlatform.repository;

import MedicalPlatform.model.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {
}
