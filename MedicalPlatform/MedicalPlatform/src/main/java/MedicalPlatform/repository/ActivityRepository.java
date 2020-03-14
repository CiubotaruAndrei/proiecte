package MedicalPlatform.repository;

import MedicalPlatform.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query
    List<Activity> getActivitiesByPatientId(Integer id);

    @Query(
            value = "SELECT * FROM activity WHERE patient_id = ?1 AND start LIKE ?2",
            nativeQuery = true)
    List<Activity> getActivitiesHistory(Integer id, String date);

    @Query(
            value = "SELECT recommendation FROM activity WHERE patient_id = ?1 AND unusual = 1 AND recommendation IS NOT NULL",
            nativeQuery = true)
    List<String> getUnusualActivities(Integer id);
}
