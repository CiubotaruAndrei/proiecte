package MedicalPlatform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class IntakeMed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication med;

    @ManyToOne
    @JoinColumn(name = "medplan_id")
    @JsonIgnore
    private MedicationPlan medPlan;

    private String intakeMoments;
}
