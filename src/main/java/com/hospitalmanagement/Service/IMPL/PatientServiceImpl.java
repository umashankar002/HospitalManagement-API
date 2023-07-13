package com.hospitalmanagement.Service.IMPL;

import com.hospitalmanagement.Entity.Patient;
import com.hospitalmanagement.Repository.PatientRepository;
import com.hospitalmanagement.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient admitPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public void dischargePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }
}

