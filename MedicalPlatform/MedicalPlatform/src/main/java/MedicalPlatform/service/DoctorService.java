package MedicalPlatform.service;

import MedicalPlatform.model.Doctor;
import MedicalPlatform.repository.DoctorRepository;
import MedicalPlatform.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Integer id) {
        return doctorRepository.getOne(id);
    }

    public Doctor addDoctor(Doctor doctor) {
        Doctor newDoctor = doctorRepository.save(doctor);
        if(newDoctor!=null) {
            doctorRepository.insertUser(newDoctor.getEmail(), newDoctor.getPassword());
            doctorRepository.insertAuth(newDoctor.getEmail(), "ROLE_DOCTOR");
        }
        return newDoctor;
    }

    public User login(Doctor doctor) {
        List<Doctor> doctors = getDoctors();
        for(Doctor d : doctors) {
            if(d.getEmail().equals(doctor.getEmail()) && d.getPassword().equals(doctor.getPassword()))
                return new User(d.getId(), d.getEmail(), "doctor");
        }
        return null;
    }

    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.getDoctorByEmail(email);
    }

}
