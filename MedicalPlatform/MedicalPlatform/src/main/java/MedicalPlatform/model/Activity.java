package MedicalPlatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    @JsonIgnore
    private Patient patient;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date end;

    private String activity;

    private Boolean unusual;

    private String recommendation;

    public Activity(Patient patient, Date start, Date end, String activity) {
        this.patient = patient;
        this.start = start;
        this.end = end;
        this.activity = activity;
    }

    public Activity() {
    }

    @Override
    public String toString() {
        return "Activity{" +
                "patient=" + patient +
                ", start=" + start +
                ", end=" + end +
                ", activity='" + activity + '\'' +
                '}';
    }
}
