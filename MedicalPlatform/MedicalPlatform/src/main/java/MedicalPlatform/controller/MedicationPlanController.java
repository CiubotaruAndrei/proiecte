package MedicalPlatform.controller;

import MedicalPlatform.model.MedicationPlan;
import MedicalPlatform.service.MedicationPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicationplans")
public class MedicationPlanController {

    @Autowired
    MedicationPlanService medicationPlanService;

    @GetMapping
    public List<MedicationPlan> findAll() {
        return medicationPlanService.findAll();
    }

    @PostMapping("/{patientId}")
    public MedicationPlan addMedicationPlan(@RequestBody MedicationPlan medicationPlan, @PathVariable Integer patientId) {
        return medicationPlanService.addMedicationPlan(medicationPlan, patientId);
    }

}
