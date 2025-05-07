package com.charan.employeemanagement.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charan.employeemanagement.model.SalarySlip;

public interface SalarySlipRepository extends JpaRepository<SalarySlip, Long> {
    List<SalarySlip> findByEmployeeId(Long employeeId);
Optional<SalarySlip> findTopByEmployeeIdOrderByDateIssuedDesc(Long employeeId); 
Optional<SalarySlip> findByEmployeeIdAndDateIssued(Long employeeId, LocalDate dateIssued);

boolean existsByEmployeeIdAndDateIssued(Long employeeId, LocalDate dateIssued);

}
