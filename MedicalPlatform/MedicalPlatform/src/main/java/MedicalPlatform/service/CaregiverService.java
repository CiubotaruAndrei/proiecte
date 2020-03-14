package MedicalPlatform.service;

import MedicalPlatform.exceptions.NotFoundException;
import MedicalPlatform.model.Caregiver;
import MedicalPlatform.model.Patient;
import MedicalPlatform.repository.CaregiverRepository;
import MedicalPlatform.repository.DoctorRepository;
import MedicalPlatform.util.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class CaregiverService {

    private final static String QUEUE_NAME = "activities";

    @Autowired
    CaregiverRepository caregiverRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ActivityService activityService;

    public Caregiver getCaregiverById(Integer id) {
        return caregiverRepository.findById(id).orElseThrow(() -> new NotFoundException("Caregiver with id " + id + " not found"));
    }

    public List<Caregiver> getAllCaregivers() {
        return caregiverRepository.findAll();
    }

    public Caregiver addCaregiver(Caregiver caregiver) {
        Caregiver newCaregiver = caregiverRepository.save(caregiver);
        if(newCaregiver!=null) {
            doctorRepository.insertUser(newCaregiver.getEmail(), newCaregiver.getPassword());
            doctorRepository.insertAuth(newCaregiver.getEmail(), "ROLE_CAREGIVER");
        }
        return newCaregiver;
    }

    public void deleteCaregiver(Integer id) {
        log.info("Delete caregiver");
        Caregiver caregiver = getCaregiverById(id);
        if(caregiver!=null) {
            doctorRepository.deleteUser(caregiver.getEmail());
            caregiverRepository.deleteById(id);
            log.info("Caregiver with id " + id + " deleted");
        }
    }

    public Caregiver updateCaregiver(Integer id, Caregiver caregiver) {
        log.info("Updating caregiver");
        Caregiver newCaregiver = getCaregiverById(id);
        if(newCaregiver!=null) {
            if(!caregiver.getAddress().equals("")) newCaregiver.setAddress(caregiver.getAddress());
            if(caregiver.getBirthDate()!=null) newCaregiver.setBirthDate(caregiver.getBirthDate());
            if(!caregiver.getEmail().equals("")) newCaregiver.setEmail(caregiver.getEmail());
            if(!caregiver.getName().equals("")) newCaregiver.setName(caregiver.getName());
            if(!caregiver.getGender().equals("")) newCaregiver.setGender(caregiver.getGender());
            return caregiverRepository.save(newCaregiver);
        }
        else {
            log.info("Caregiver with id " + id + " not found");
            return null;
        }
    }

    public Set<Patient> getPatients(Integer id) {
        log.info("Getting patients list for caregiver with id " + id);
        Caregiver caregiver = getCaregiverById(id);
        if(caregiver!=null)
            return caregiver.getPatients();
        else {
            log.info("Empty list");
            return null;
        }
    }

    public User login(Caregiver caregiver) {
        List<Caregiver> caregivers = getAllCaregivers();
        for(Caregiver c : caregivers) {
            if(c.getEmail().equals(caregiver.getEmail()) && c.getPassword().equals(caregiver.getPassword()))
                return new User(c.getId(), c.getEmail(), "caregiver");
        }
        return null;
    }

    public Caregiver getCaregiverByEmail(String email) {
        return caregiverRepository.getCaregiverByEmail(email);
    }

    public List<String> getRecommendations(Integer patientId) {
        return activityService.getUnusualActivities(patientId);
    }
}
