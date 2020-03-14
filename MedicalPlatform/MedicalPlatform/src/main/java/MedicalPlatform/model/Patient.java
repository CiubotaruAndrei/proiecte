package MedicalPlatform.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String gender;

    private String address;

    private String medicalRecord;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    Set<MedicationPlan> medicationPlans;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    Set<Activity> activities;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    Set<MedicationMonitor> medicationMonitors;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="caregiver_id")
    private Caregiver caregiver;

}
