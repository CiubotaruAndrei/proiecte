package MedicalPlatform.util;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String email;

    private String role;

    public User(Integer id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
