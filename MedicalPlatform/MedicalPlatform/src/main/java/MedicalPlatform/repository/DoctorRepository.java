package MedicalPlatform.repository;

import MedicalPlatform.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query
    Doctor getDoctorByEmail(String email);

    @Modifying
    @Transactional
    @Query
            (
            value = "INSERT INTO users VALUES (?1, ?2, true);",
            nativeQuery = true)
    void insertUser(String username, String password);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO authorities VALUES (?1, ?2);",
            nativeQuery = true)
    void insertAuth(String username, String role);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM users WHERE username=?1",
            nativeQuery = true)
    void deleteUser(String username);
}
