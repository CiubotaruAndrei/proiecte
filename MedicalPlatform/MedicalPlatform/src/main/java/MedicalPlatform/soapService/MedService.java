package MedicalPlatform.soapService;

import MedicalPlatform.model.MedicationMonitor;
import MedicalPlatform.repository.MedMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import springsoap.ArrayOfMeds;
import springsoap.Med;
import springsoap.MedRequest;
import springsoap.MedResponse;

import java.util.List;

@Endpoint
public class MedService {

    @Autowired
    MedMonitorRepository medMonitorRepository;

    @PayloadRoot(namespace = "http://springsoap", localPart = "medRequest")
    @ResponsePayload
    public MedResponse getPatientActivityHistory(@RequestPayload MedRequest request) {

        List<MedicationMonitor> meds = medMonitorRepository.getDailyMeds(request.getId(), request.getDate()+"%");
        MedResponse response = new MedResponse();
        ArrayOfMeds responseMeds = new ArrayOfMeds();
        for(MedicationMonitor m: meds) {
            Med medResponse = new Med();
            medResponse.setName(m.getMedName());
            medResponse.setTime(m.getExpectedTime());
            medResponse.setTaken(m.getTaken());
            responseMeds.getMed().add(medResponse);
        }
        response.setMedications(responseMeds);
        return response;
    }
}
