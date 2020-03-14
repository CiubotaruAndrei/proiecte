package MedicalPlatformSoapClient.controller;

import MedicalPlatformSoapClient.model.ActivityDTO;
import MedicalPlatformSoapClient.model.Recommendation;
import MedicalPlatformSoapClient.service.ActivityService;
import MedicalPlatformSoapClient.service.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soapservice.Activity;
import soapservice.Med;
import soapservice.MedResponse;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class Controller {

    @Autowired
    ActivityService activityService;

    @Autowired
    MedService medService;

    @GetMapping("/history/{id}/{date}")
    public List<ActivityDTO> getActivityHistory(@PathVariable Integer id, @PathVariable String date) {
        return activityService.getActivityHistory(id,date);
    }

    @GetMapping("/dailymeds/{id}/{date}")
    public List<Med> getDailyMeds(@PathVariable Integer id, @PathVariable String date) {
        return medService.getDailyMeds(id,date);
    }

    @GetMapping("/unusual/{id}/{date}")
    public List<Activity> getUnusualActivities(@PathVariable Integer id, @PathVariable String date) {
        return activityService.getUnusualActivities(id,date);
    }

    @PutMapping("/addRecommendation")
    public Integer addRecommendation(@RequestBody Recommendation rec) {
        return activityService.addRecommendation(rec.getId(), rec.getText());
    }
}
