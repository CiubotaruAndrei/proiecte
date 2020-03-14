package MedicalPlatform.service;

import MedicalPlatform.exceptions.NotFoundException;
import MedicalPlatform.model.Medication;
import MedicalPlatform.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public Medication findById(Integer id) {
        return medicationRepository.findById(id).orElseThrow(() -> new NotFoundException("Medication with id" + id + " not found"));
    }

    public Medication addMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public void deleteMedication(Integer id) {
        log.info("Delete medication");
        Medication medication = findById(id);
        if(medication!=null) {
            medicationRepository.deleteById(id);
            log.info("Medication with id " + id + " deleted");
        }
    }

    public Medication getMedicationByName(String name) {
        return medicationRepository.getMedicationByName(name);
    }

    public Medication updateMedication(Integer id, Medication medication) {
        Medication newMedication = findById(id);
        if(newMedication!=null) {
            if(!medication.getName().equals("")) newMedication.setName(medication.getName());
            if(!medication.getDosage().equals("")) newMedication.setDosage(medication.getDosage());
            if(!medication.getSideEffects().equals("")) newMedication.setSideEffects(medication.getSideEffects());
            return medicationRepository.save(newMedication);
        }
        else {
            log.info("Medication with id " + id + " not found");
            return null;
        }
    }


}
