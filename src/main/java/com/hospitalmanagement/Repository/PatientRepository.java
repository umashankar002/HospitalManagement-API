package com.hospitalmanagement.Repository;

import com.hospitalmanagement.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
