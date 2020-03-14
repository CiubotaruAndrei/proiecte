package MedicalPlatform.controller;

import MedicalPlatform.model.Caregiver;
import MedicalPlatform.model.Patient;
import MedicalPlatform.service.CaregiverService;
import MedicalPlatform.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/caregivers")
public class CaregiverController {

    @Autowired
    CaregiverService caregiverService;

    @GetMapping
    public List<Caregiver> findAll() {
        return caregiverService.getAllCaregivers();
    }

    @GetMapping("/{id}")
    public Caregiver findById(@PathVariable Integer id) {
        return caregiverService.getCaregiverById(id);
    }

    @PostMapping
    public Caregiver addCaregiver(@RequestBody Caregiver caregiver) {
        return caregiverService.addCaregiver(caregiver);
    }

    @DeleteMapping("/{id}")
    public void deleteCaregiver(@PathVariable Integer id) {
        caregiverService.deleteCaregiver(id);
    }

    @PutMapping("/{id}")
    public Caregiver updateCaregiver(@PathVariable Integer id, @RequestBody Caregiver caregiver) {
        return caregiverService.updateCaregiver(id, caregiver);
    }

    @GetMapping("/{id}/patients")
    public Set<Patient> getPatients(@PathVariable Integer id) {
        return caregiverService.getPatients(id);
    }

    @PostMapping("/login")
    public User caregiverLogin(@RequestBody Caregiver caregiver) {
        return caregiverService.login(caregiver);
    }

    @GetMapping("/email/{email}")
    public Caregiver getCaregiverByEmail(@PathVariable String email) {
        return caregiverService.getCaregiverByEmail(email);
    }

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/alert")
    public void alert(String message, Integer caregiverId) {
        System.out.println("Alert caregiver with id " + caregiverId);
        this.template.convertAndSend("/queue/" + caregiverId, message);
    }

    @GetMapping("/rec/{id}")
    public List<String> getRecommendations(@PathVariable Integer id) {
        return caregiverService.getRecommendations(id);
    }
}
