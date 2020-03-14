package MedicalPlatform.util;

import lombok.Getter;

@Getter
public class ActivityDTO {

    private String idPatient;

    private String start;

    private String end;

    private String activity;

    public ActivityDTO(String idPatient, String start, String end, String activity) {
        this.idPatient = idPatient;
        this.start = start;
        this.end = end;
        this.activity = activity;
    }
}
