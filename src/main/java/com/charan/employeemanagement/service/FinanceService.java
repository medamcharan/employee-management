package com.charan.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charan.employeemanagement.model.Employee;
import com.charan.employeemanagement.model.SalarySlip;
import com.charan.employeemanagement.repository.EmployeeRepository;
import com.charan.employeemanagement.repository.SalarySlipRepository;

@Service
public class FinanceService {

    private final EmployeeRepository employeeRepository;
    private final SalarySlipRepository salarySlipRepository;

    @Autowired
    public FinanceService(EmployeeRepository employeeRepository, SalarySlipRepository salarySlipRepository) {
        this.employeeRepository = employeeRepository;
        this.salarySlipRepository = salarySlipRepository;
    }

    public SalarySlip calculateSalary(Long employeeId, double bonus) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double baseSalary = employee.getBasicSalary();
        double grossSalary = baseSalary + bonus;
        double tax = 0.1 * grossSalary;
        double netSalary = grossSalary - tax;

        SalarySlip slip = new SalarySlip();
        slip.setEmployee(employee);
        slip.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
        slip.setGrossSalary(grossSalary);
        slip.setTaxDeduction(tax);
        slip.setNetSalary(netSalary);
        slip.setBonus(bonus); // Add bonus to the slip

        SalarySlip savedSlip = salarySlipRepository.save(slip);

        // Set the bonus and gross salary details in the response for clarity
        savedSlip.setBonus(bonus); // Ensure bonus is returned in the response
        savedSlip.setBonusAdded("₹" + bonus + " has been added to the base salary.");

        return savedSlip;
    }

    public List<SalarySlip> getAllSalarySlips() {
        return salarySlipRepository.findAll();
    }

    public SalarySlip getSalarySlipByEmployeeId(Long employeeId) {
        return salarySlipRepository.findTopByEmployeeIdOrderByDateIssuedDesc(employeeId)
                .orElseThrow(() -> new RuntimeException("Salary slip not found for employee ID: " + employeeId));
    }

    public List<SalarySlip> getAllSalarySlipsByEmployeeId(Long employeeId) {
        return salarySlipRepository.findByEmployeeId(employeeId);
    }

    public SalarySlip updateSalarySlipById(Long slipId, SalarySlip updatedSlip) {
        // Find the existing salary slip by slip ID
        SalarySlip existingSlip = salarySlipRepository.findById(slipId)
                .orElseThrow(() -> new RuntimeException("Salary slip not found for ID: " + slipId));
    
        // Update the salary details
        existingSlip.setGrossSalary(updatedSlip.getGrossSalary());
        existingSlip.setTaxDeduction(updatedSlip.getTaxDeduction());
        existingSlip.setNetSalary(updatedSlip.getNetSalary());
    
        // Save the updated salary slip
        return salarySlipRepository.save(existingSlip);
    }
    

    public void deleteSalarySlipById(Long slipId) {
        SalarySlip slip = salarySlipRepository.findById(slipId)
                .orElseThrow(() -> new RuntimeException("Salary slip not found for ID: " + slipId));
        salarySlipRepository.delete(slip);
    }

}

    

    