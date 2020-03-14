package MedicalPlatform.service;

import MedicalPlatform.exceptions.NotFoundException;
import MedicalPlatform.model.Caregiver;
import MedicalPlatform.model.Doctor;
import MedicalPlatform.model.MedicationPlan;
import MedicalPlatform.model.Patient;
import MedicalPlatform.repository.CaregiverRepository;
import MedicalPlatform.repository.DoctorRepository;
import MedicalPlatform.repository.PatientRepository;
import MedicalPlatform.util.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    CaregiverRepository caregiverRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient with id " + id + " not found"));
    }

    public Patient addPatient(Patient patient, Integer doctorId, Integer caregiverId) {
        Doctor doctor = doctorRepository.getOne(doctorId);
        patient.setDoctor(doctor);
        Caregiver caregiver =  caregiverRepository.getOne(caregiverId);
        patient.setCaregiver(caregiver);

        Patient newPatient = patientRepository.save(patient);
        if(newPatient!=null) {
            doctorRepository.insertUser(newPatient.getEmail(),newPatient.getPassword());
            doctorRepository.insertAuth(newPatient.getEmail(),"ROLE_PATIENT");
        }
        return newPatient;
    }

    public void deletePatient(Integer id) {
        log.info("Delete patient");
        Patient patient = getPatientById(id);
        if(patient!=null) {
            doctorRepository.deleteUser(patient.getEmail());
            patientRepository.deleteById(id);
            log.info("Patient with id " + id + " deleted");
        }
    }

    public Patient updatePatient(Integer id, Integer caregiverId, Patient patient) {
        log.info("Updating patient");
        Patient newPatient = getPatientById(id);
        if(newPatient!=null) {
            if(!patient.getAddress().equals("")) newPatient.setAddress(patient.getAddress());
            if(patient.getBirthDate()!=null) newPatient.setBirthDate(patient.getBirthDate());
            if(!patient.getEmail().equals("")) newPatient.setEmail(patient.getEmail());
            if(!patient.getMedicalRecord().equals("")) newPatient.setMedicalRecord(patient.getMedicalRecord());
            if(!patient.getName().equals("")) newPatient.setName(patient.getName());
            if(!patient.getGender().equals("")) newPatient.setGender(patient.getGender());
            if(caregiverId!=null) newPatient.setCaregiver(caregiverRepository.getOne(caregiverId));
            return patientRepository.save(newPatient);
        }
        else {
            log.info("Patient with id " + id + " not found");
            return null;
        }
    }

    public Set<MedicationPlan> getMedicationPlans(Integer id) {
        log.info("Getting medication plans for patient with id " + id);
        Patient patient = getPatientById(id);
        if(patient!=null)
            return patient.getMedicationPlans();
        else {
            log.info("Empty list");
            return null;
        }
    }

    public User login(Patient patient) {
        List<Patient> patients = getAllPatients();
        for(Patient p : patients) {
            if(p.getEmail().equals(patient.getEmail()) && p.getPassword().equals(patient.getPassword()))
                return new User(p.getId(), p.getEmail(), "patient");
        }
        return null;
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.getPatientByEmail(email);
    }
}
