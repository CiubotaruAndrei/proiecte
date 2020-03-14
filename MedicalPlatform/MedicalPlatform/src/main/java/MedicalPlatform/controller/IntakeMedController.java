package MedicalPlatform.controller;

import MedicalPlatform.model.IntakeMed;
import MedicalPlatform.service.IntakeMedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intake")
public class IntakeMedController {

    @Autowired
    IntakeMedService intakeMedService;

    @PostMapping("/{medicationId}/{medicationPlanId}")
    public IntakeMed addIntake(@RequestBody IntakeMed intake, @PathVariable Integer medicationId, @PathVariable Integer medicationPlanId) {
        return intakeMedService.addIntake(intake, medicationId, medicationPlanId);
    }
}
