package MedicalPlatform.service;


import MedicalPlatform.model.MedicationPlan;
import MedicalPlatform.model.Patient;
import MedicalPlatform.repository.MedicationPlanRepository;
import MedicalPlatform.repository.MedicationRepository;
import MedicalPlatform.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MedicationPlanService {

    @Autowired
    MedicationPlanRepository medicationPlanRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicationRepository medicationRepository;

    public List<MedicationPlan> findAll() {
        return medicationPlanRepository.findAll();
    }

    public MedicationPlan addMedicationPlan(MedicationPlan medicationPlan, Integer patientId) {
        Patient patient = patientRepository.getOne(patientId);
        medicationPlan.setPatient(patient);
        return medicationPlanRepository.save(medicationPlan);
    }
}

