package MedicalPlatform;

import MedicalPlatform.controller.CaregiverController;
import MedicalPlatform.model.Activity;
import MedicalPlatform.model.Patient;
import MedicalPlatform.service.ActivityService;
import MedicalPlatform.service.PatientService;
import MedicalPlatform.util.ActivityDTO;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Consumer  implements CommandLineRunner{

    private final static String QUEUE_NAME = "activities";

    @Autowired
    ActivityService activityService;

    @Autowired
    PatientService patientService;

    @Autowired
    CaregiverController caregiverController;

    @Override
    public void run(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages...");

        Gson gson = new Gson();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            ActivityDTO activityDTO = gson.fromJson(message, ActivityDTO.class);
            Patient patient = patientService.getPatientById(Integer.parseInt(activityDTO.getIdPatient()));
            boolean ok = false;
            try {
                Date start = df.parse(activityDTO.getStart());
                Date end = df.parse(activityDTO.getEnd());
                Activity activity = new Activity(patient, start, end, activityDTO.getActivity());
                long period = (end.getTime() - start.getTime())/3600000;
                System.out.println("Activity:" + activity.getActivity() + " Period: " + period);
                if(activity.getActivity().equals("Sleeping") && period>12) {
                    caregiverController.alert("Patient " + patient.getName() + " slept for more than 12h", patient.getCaregiver().getId());
                    ok = true;
                }
                else if(activity.getActivity().equals("Leaving") && period>12) {
                    caregiverController.alert("Patient " + patient.getName() + " is outdoor for more than 12h", patient.getCaregiver().getId());
                    ok = true;
                }
                else if((activity.getActivity().equals("Showering") || activity.getActivity().equals("Toileting")) && period>1) {
                    caregiverController.alert("Patient " + patient.getName() + " is in bathroom for more than 1h", patient.getCaregiver().getId());
                    ok = true;
                }
                activity.setUnusual(ok);
                activityService.addActivity(activity);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
