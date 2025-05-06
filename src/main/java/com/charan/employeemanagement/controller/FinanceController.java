package com.charan.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.charan.employeemanagement.model.SalarySlip;
import com.charan.employeemanagement.service.FinanceService;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    private final FinanceService financeService;

    @Autowired
    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    // Endpoint to calculate and generate salary slip
    @PostMapping("/salary-slip/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public SalarySlip generateSalarySlip(@PathVariable Long employeeId, @RequestParam double bonus) {
        return financeService.calculateSalary(employeeId, bonus);
    }

    // Endpoint to fetch all salary slips
    @GetMapping("/salary-slips")
    public List<SalarySlip> getAllSalarySlips() {
        return financeService.getAllSalarySlips();
    }

    // Endpoint to fetch a salary slip by employee ID
    @GetMapping("/salary-slip/{employeeId}")
    public SalarySlip getSalarySlipByEmployeeId(@PathVariable Long employeeId) {
        return financeService.getSalarySlipByEmployeeId(employeeId);
    }

    // Endpoint to fetch all salary slips by employee ID
    @GetMapping("/salary-slips/{employeeId}")
    public List<SalarySlip> getAllSalarySlipsByEmployeeId(@PathVariable Long employeeId) {
        return financeService.getAllSalarySlipsByEmployeeId(employeeId);
    }

    // Endpoint to update a salary slip
    @PutMapping("/salary-slip/{slipId}")
    public SalarySlip updateSalarySlip(@PathVariable Long slipId, @RequestBody SalarySlip updatedSlip) {
        return financeService.updateSalarySlipById(slipId, updatedSlip);
    }

    // Endpoint to delete a salary slip
    @DeleteMapping("/salary-slip/{slipId}")
    public void deleteSalarySlip(@PathVariable Long slipId) {
        financeService.deleteSalarySlipById(slipId);
    }
}
