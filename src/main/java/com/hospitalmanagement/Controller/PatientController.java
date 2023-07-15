package com.hospitalmanagement.Controller;
import com.hospitalmanagement.Entity.Patient;
import com.hospitalmanagement.Service.IMPL.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientServiceImpl patientService;

    @Autowired
    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasRole('STAFF')")
    @PostMapping
    public ResponseEntity<Patient> admitPatient(@RequestBody Patient patient) {
        Patient admittedPatient = patientService.admitPatient(patient);
        return new ResponseEntity<>(admittedPatient, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('STAFF')")
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('STAFF')")
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> dischargePatient(@PathVariable Long patientId) {
        patientService.dischargePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

