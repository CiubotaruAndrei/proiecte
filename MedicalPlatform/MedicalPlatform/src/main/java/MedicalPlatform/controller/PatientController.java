package MedicalPlatform.controller;

import MedicalPlatform.model.Doctor;
import MedicalPlatform.model.MedicationPlan;
import MedicalPlatform.model.Patient;
import MedicalPlatform.service.DoctorService;
import MedicalPlatform.service.PatientService;
import MedicalPlatform.util.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    public List<Patient> findAll() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Integer id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/{doctorId}/{caregiverId}")
    public Patient addPatient(@RequestBody Patient patient, @PathVariable Integer doctorId, @PathVariable Integer caregiverId) {
        System.out.println(patient.toString());
        return patientService.addPatient(patient, doctorId, caregiverId);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}/{caregiverId}")
    public Patient updatePatient(@PathVariable Integer id, @PathVariable Integer caregiverId, @RequestBody Patient patient) {
        return patientService.updatePatient(id, caregiverId, patient);
    }

    @GetMapping("/{id}/medicationplan")
    public Set<MedicationPlan> getMedicationPlans(@PathVariable Integer id) {
        return patientService.getMedicationPlans(id);
    }

    @PostMapping("/login")
    public User patientLogin(@RequestBody Patient patient) {
        return patientService.login(patient);
    }

    @GetMapping("/email/{email}")
    public Patient getPatientByEmail(@PathVariable String email) {
        return patientService.getPatientByEmail(email);
    }

}
