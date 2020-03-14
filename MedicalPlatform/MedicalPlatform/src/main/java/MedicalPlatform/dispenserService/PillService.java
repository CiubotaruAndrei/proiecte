package MedicalPlatform.dispenserService;

import MedicalPlatform.controller.CaregiverController;
import MedicalPlatform.model.IntakeMed;
import MedicalPlatform.model.MedicationMonitor;
import MedicalPlatform.model.MedicationPlan;
import MedicalPlatform.model.Patient;
import MedicalPlatform.repository.MedMonitorRepository;
import MedicalPlatform.service.PatientService;
import MedicalPlatform.util.User;
import grpc.UserGrpc.*;
import grpc.UserOuterClass.*;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


@Component
public class PillService extends UserImplBase {

    @Autowired
    PatientService patientService;

    @Autowired
    MedMonitorRepository medMonitorRepository;

    @Autowired
    CaregiverController caregiverController;

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        System.out.println("[pillDispenser] login " + request.getEmail());

        Patient patient = new Patient();
        patient.setEmail(request.getEmail());
        patient.setPassword(request.getPassword());
        User user = patientService.login(patient);

        LoginResponse.Builder response = LoginResponse.newBuilder();
        if(user!=null) {
            response.setId(user.getId());
            response.setName(patientService.getPatientById(user.getId()).getName());
        }
        else
            response.setId(0);

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getMed(MedRequest request, StreamObserver<MedResponse> responseObserver) {
        System.out.println("[pillDispenser] get med plan for user " + request.getId());

        MedResponse.Builder response = MedResponse.newBuilder();
        Patient patient = patientService.getPatientById(request.getId());
        for(MedicationPlan plan : patient.getMedicationPlans()) {
            String start = plan.getStart().toString();
            String end = plan.getEnd().toString();
            if(request.getTime().compareTo(start)>=0 && request.getTime().compareTo(end)<=0) {
                for (IntakeMed med : plan.getIntakeMeds()) {
                    response.addMedBuilder().setName(med.getMed().getName())
                            .setDosage(med.getMed().getDosage())
                            .setMoments(med.getIntakeMoments())
                            .build();
                }
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }


    @Override
    public void medTake(TakeMedRequest request, StreamObserver<Empty> responseObserver) {
        Patient patient = patientService.getPatientById(request.getId());
        System.out.println("[pillDispenser] " + request.getActualTime() + " patient " + patient.getName() + " take med " + request.getName());
        medMonitorRepository.save(new MedicationMonitor(patient,request.getName(),request.getExpectedTime(),request.getActualTime(),true));
        Empty.Builder response = Empty.newBuilder();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void medNotTake(NotTakeMedRequest request, StreamObserver<Empty> responseObserver) {
        for(TakeMedRequest med : request.getMedList()) {
            Patient patient = patientService.getPatientById(med.getId());
            System.out.println("[pillDispenser] " + med.getActualTime() + " patient " + patient.getName() + " didn't take med " + med.getName());
            medMonitorRepository.save(new MedicationMonitor(patient,med.getName(),med.getExpectedTime(),med.getActualTime(),false));
            caregiverController.alert("Patient " + patient.getName() + " didn't take med " + med.getName(), patientService.getPatientById(request.getMed(0).getId()).getCaregiver().getId());
        }
        Empty.Builder response = Empty.newBuilder();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
