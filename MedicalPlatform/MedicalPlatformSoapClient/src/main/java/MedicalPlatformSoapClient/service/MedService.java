package MedicalPlatformSoapClient.service;

import MedicalPlatformSoapClient.SOAPConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soapservice.Med;
import soapservice.MedRequest;
import soapservice.MedResponse;

import java.util.List;

@Service
public class MedService {

    @Autowired
    SOAPConnector soapConnector;

    public List<Med> getDailyMeds(Integer patientId, String date) {
        MedRequest request = new MedRequest();
        request.setId(patientId);
        request.setDate(date);

        MedResponse soapResponse = (MedResponse) soapConnector.callWebService("http://localhost:8080/soap", request);

        return soapResponse.getMedications().getMed();
    }
}
