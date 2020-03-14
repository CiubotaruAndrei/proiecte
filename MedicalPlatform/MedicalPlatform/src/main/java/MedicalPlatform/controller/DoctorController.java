package MedicalPlatform.controller;

import MedicalPlatform.model.Doctor;
import MedicalPlatform.service.DoctorService;
import MedicalPlatform.util.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<Doctor> findAll() {
        log.info("Get all doctors");
        return doctorService.getDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        log.info("Add a new doctor in database");
        System.out.println(doctor.toString());
        return doctorService.addDoctor(doctor);
    }

    @PostMapping("/login")
    public User doctorLogin(@RequestBody Doctor doctor) {
        return doctorService.login(doctor);
    }

    @GetMapping("/{email}")
    public Doctor getDoctorByEmail(@PathVariable String email) {
        return doctorService.getDoctorByEmail(email);
    }

}
