package MedicalPlatformSoapClient.service;

import MedicalPlatformSoapClient.SOAPConnector;
import MedicalPlatformSoapClient.model.ActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapservice.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    SOAPConnector soapConnector;

    public List<ActivityDTO> getActivityHistory(Integer patientId, String date) {
        PatientHistoryRequest request = new PatientHistoryRequest();
        request.setId(patientId);
        request.setDate(date);
        PatientHistoryResponse response = (PatientHistoryResponse) soapConnector.callWebService("http://localhost:8080/soap", request);

        List<ActivityDTO> activities = new ArrayList<>();
        activities.add(new ActivityDTO("Sleeping", 0));
        activities.add(new ActivityDTO("Breakfast", 0));
        activities.add(new ActivityDTO("Grooming", 0));
        activities.add(new ActivityDTO("Toileting", 0));
        activities.add(new ActivityDTO("Snack", 0));
        activities.add(new ActivityDTO("Spare_Time/TV", 0));
        activities.add(new ActivityDTO("Leaving", 0));
        activities.add(new ActivityDTO("Lunch", 0));

        for(Activity responseActivity: response.getActivities().getActivity()) {
            for(ActivityDTO a: activities) {
                if(a.getName().equals(responseActivity.getName()))
                    a.setDuration(a.getDuration()+responseActivity.getDuration());
            }
        }
        return activities;
    }

    public List<Activity> getUnusualActivities(Integer patientId, String date) {
        PatientHistoryRequest request = new PatientHistoryRequest();
        request.setId(patientId);
        request.setDate(date);
        PatientHistoryResponse response = (PatientHistoryResponse) soapConnector.callWebService("http://localhost:8080/soap", request);

        List<Activity> activities = new ArrayList<>();

        for(Activity responseActivity: response.getActivities().getActivity()) {
            if(responseActivity.isUnusual())
                activities.add(responseActivity);
        }
        return activities;
    }

    public Integer addRecommendation(Integer id, String recommend) {
        RecommendRequest request = new RecommendRequest();
        request.setId(id);
        request.setRecommendation(recommend);
        RecommendResponse response = (RecommendResponse) soapConnector.callWebService("http://localhost:8080/soap", request);
        return response.getCode();
    }
}
