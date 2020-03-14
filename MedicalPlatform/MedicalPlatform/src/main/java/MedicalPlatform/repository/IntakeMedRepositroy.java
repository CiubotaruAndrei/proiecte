package MedicalPlatform.repository;

import MedicalPlatform.model.IntakeMed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntakeMedRepositroy extends JpaRepository<IntakeMed, Integer> {
}
