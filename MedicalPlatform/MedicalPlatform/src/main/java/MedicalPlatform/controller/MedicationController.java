package MedicalPlatform.controller;

import MedicalPlatform.model.Medication;
import MedicalPlatform.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    MedicationService medicationService;

    @GetMapping
    public List<Medication> findAll() {
        return medicationService.findAll();
    }

    @PostMapping
    public Medication addMedication(@RequestBody Medication medication) {
        return medicationService.addMedication(medication);
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Integer id) {
        medicationService.deleteMedication(id);
    }

    @GetMapping("/name/{name}")
    public Medication getMedicationByName(@PathVariable String name) {
        return medicationService.getMedicationByName(name);
    }

    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Integer id, @RequestBody Medication medication) {
        return medicationService.updateMedication(id, medication);
    }

    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Integer id) {
        return medicationService.findById(id);
    }
}
