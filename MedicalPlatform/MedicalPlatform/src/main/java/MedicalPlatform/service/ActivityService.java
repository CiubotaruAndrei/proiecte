package MedicalPlatform.service;

import MedicalPlatform.exceptions.NotFoundException;
import MedicalPlatform.model.Activity;
import MedicalPlatform.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivityById(Integer id) {
        return  activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity with id " + id + " not found"));
    }

    public List<Activity> getActivityHistory(Integer patientId, String date) {
        return activityRepository.getActivitiesHistory(patientId,date+"%");
    }

    public List<String> getUnusualActivities(Integer id) {
        return activityRepository.getUnusualActivities(id);
    }
}
