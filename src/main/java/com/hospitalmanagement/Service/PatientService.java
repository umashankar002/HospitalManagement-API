package com.hospitalmanagement.Service;

import com.hospitalmanagement.Entity.Patient;

import java.util.List;

public interface PatientService {
    Patient admitPatient(Patient patient);

    List<Patient> getAllPatients();

    void dischargePatient(Long patientId);
}
