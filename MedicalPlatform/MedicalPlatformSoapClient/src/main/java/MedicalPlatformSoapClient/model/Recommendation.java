package MedicalPlatformSoapClient.model;

public class Recommendation {

    private Integer id;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Recommendation(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
}
