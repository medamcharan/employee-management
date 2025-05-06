package com.charan.employeemanagement.repository;


import com.charan.employeemanagement.model.SalarySlip;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarySlipRepository extends JpaRepository<SalarySlip, Long> {
    List<SalarySlip> findByEmployeeId(Long employeeId);
Optional<SalarySlip> findTopByEmployeeIdOrderByDateIssuedDesc(Long employeeId); // For latest slip

}
