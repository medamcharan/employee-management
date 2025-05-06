package com.charan.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charan.employeemanagement.model.Employee;
import com.charan.employeemanagement.model.SalarySlip;
import com.charan.employeemanagement.repository.EmployeeRepository;
import com.charan.employeemanagement.repository.SalarySlipRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SalarySlipRepository salarySlipRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SalarySlipRepository salarySlipRepository) {
        this.employeeRepository = employeeRepository;
        this.salarySlipRepository = salarySlipRepository;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    public SalarySlip viewSalarySlip(Long employeeId) {
        // Assuming findByEmployeeId returns a List of SalarySlip
        List<SalarySlip> salarySlips = salarySlipRepository.findByEmployeeId(employeeId);
        return salarySlips.stream()
                .findFirst() // Get the first element if exists
                .orElseThrow(() -> new RuntimeException("Salary slip not found for employee ID: " + employeeId));
    }
}
