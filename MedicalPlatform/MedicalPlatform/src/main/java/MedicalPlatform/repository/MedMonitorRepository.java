package MedicalPlatform.repository;

import MedicalPlatform.model.MedicationMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedMonitorRepository extends JpaRepository<MedicationMonitor, Integer> {

    @Query(
            value = "SELECT * FROM medication_monitor WHERE patient_id = ?1 AND expected_time LIKE ?2",
            nativeQuery = true)
    List<MedicationMonitor> getDailyMeds(Integer id, String date);
}
