package MedicalPlatform.util;

import MedicalPlatform.model.Caregiver;
import MedicalPlatform.model.Doctor;
import MedicalPlatform.model.Patient;
import MedicalPlatform.repository.CaregiverRepository;
import MedicalPlatform.repository.DoctorRepository;
import MedicalPlatform.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/doctors/login", "/caregivers/login", "/patients/login", "/doctors/*", "/caregivers/rec/*").permitAll()
                .antMatchers(HttpMethod.POST, "/doctors/**").permitAll()
                .antMatchers("/patients/*").hasAnyRole("PATIENT", "DOCTOR")
                .antMatchers("/caregivers/*", "/caregivers/*/patients").hasAnyRole("CAREGIVER", "DOCTOR")
                .antMatchers("/doctors/**").hasAnyRole("DOCTOR")
                .antMatchers("/caregivers/**").hasAnyRole("DOCTOR")
                .antMatchers("/medications/**").hasAnyRole("DOCTOR")
                .antMatchers("/patients/**").hasAnyRole("DOCTOR")
                .antMatchers("/medicationplans/**").hasAnyRole("DOCTOR")
                .antMatchers("/intake/**").hasAnyRole("DOCTOR")
                .antMatchers("/ws/**").hasAnyRole("CAREGIVER")
                .antMatchers("/soap/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    /*
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<Doctor> doctors = doctorRepository.findAll();
        if(doctors!=null){
            for(Doctor d: doctors)
                auth.inMemoryAuthentication()
                        .withUser(d.getEmail()).password(encoder().encode(d.getPassword())).roles("DOCTOR");
        }

        List<Caregiver>  caregivers = caregiverRepository.findAll();
        if(caregivers!=null){
            for(Caregiver c: caregivers)
                auth.inMemoryAuthentication()
                        .withUser(c.getEmail()).password(encoder().encode(c.getPassword())).roles("CAREGIVER");
        }

        List<Patient> patients = patientRepository.findAll();
        if(patients!=null){
            for(Patient p: patients)
                auth.inMemoryAuthentication()
                        .withUser(p.getEmail()).password(encoder().encode(p.getPassword())).roles("PATIENT");
        }
    }
    */

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).dataSource(dataSource);
    }
}
