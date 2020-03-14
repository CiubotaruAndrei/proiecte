package MedicalPlatform.service;

import MedicalPlatform.model.IntakeMed;
import MedicalPlatform.model.Medication;
import MedicalPlatform.model.MedicationPlan;
import MedicalPlatform.repository.IntakeMedRepositroy;
import MedicalPlatform.repository.MedicationPlanRepository;
import MedicalPlatform.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntakeMedService {

    @Autowired
    IntakeMedRepositroy intakeMedRepositroy;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    MedicationPlanRepository medicationPlanRepository;

    public IntakeMed addIntake(IntakeMed intake, Integer medId, Integer medplanId) {
        Medication med = medicationRepository.getOne(medId);
        intake.setMed(med);
        MedicationPlan medPlan = medicationPlanRepository.getOne(medplanId);
        intake.setMedPlan(medPlan);
        return intakeMedRepositroy.save(intake);
    }
}
