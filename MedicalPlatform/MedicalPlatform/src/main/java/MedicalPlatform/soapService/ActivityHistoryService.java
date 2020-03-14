package MedicalPlatform.soapService;

import MedicalPlatform.model.Activity;
import MedicalPlatform.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import springsoap.*;

import java.util.List;

@Endpoint
public class ActivityHistoryService {

    @Autowired
    ActivityService activityService;

    @PayloadRoot(namespace = "http://springsoap", localPart = "patientHistoryRequest")
    @ResponsePayload
    public PatientHistoryResponse getPatientActivityHistory(@RequestPayload PatientHistoryRequest request) {

        List<Activity> activities = activityService.getActivityHistory(request.getId(), request.getDate());
        PatientHistoryResponse response = new PatientHistoryResponse();
        ArrayOfActivities responseActivities = new ArrayOfActivities();
        for(Activity a: activities) {
            springsoap.Activity responseActivity = new springsoap.Activity();
            responseActivity.setId(a.getId());
            //System.out.println("Activity name: " + a.getActivity());
            responseActivity.setName(a.getActivity());
            long period = (a.getEnd().getTime()-a.getStart().getTime())/60000;
            //System.out.println("interval: %lf" + period);
            responseActivity.setDuration(period);
            responseActivity.setUnusual(a.getUnusual());
            responseActivities.getActivity().add(responseActivity);
        }

        response.setActivities(responseActivities);
        return response;
    }

    @PayloadRoot(namespace = "http://springsoap", localPart = "recommendRequest")
    @ResponsePayload
    public RecommendResponse addRecommendationActivity(@RequestPayload RecommendRequest request) {

        Activity activity = activityService.getActivityById(request.getId());
        activity.setRecommendation(request.getRecommendation());
        Activity updateActivity = activityService.addActivity(activity);
        RecommendResponse response = new RecommendResponse();
        if(updateActivity == null)
            response.setCode(500);
        else
            response.setCode(200);
        return response;
    }
}
