package MedicalPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MedicationMonitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    private String medName;

    private String expectedTime;

    private String actualTime;

    private Boolean taken;

    public MedicationMonitor(Patient patient, String medName, String expectedTime, String actualTime, Boolean taken) {
        this.patient = patient;
        this.medName = medName;
        this.expectedTime = expectedTime;
        this.actualTime = actualTime;
        this.taken = taken;
    }

    public MedicationMonitor() {}
}
