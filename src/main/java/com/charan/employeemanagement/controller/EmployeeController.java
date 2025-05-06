package com.charan.employeemanagement.controller;

import com.charan.employeemanagement.model.Employee;
import com.charan.employeemanagement.model.SalarySlip;
import com.charan.employeemanagement.service.EmployeeService;
import com.charan.employeemanagement.service.FinanceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('EMPLOYEE')")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
     private final FinanceService financeService;
     

    
    @Autowired
public EmployeeController(EmployeeService employeeService, FinanceService financeService) {
    this.employeeService = employeeService;
    this.financeService = financeService;
}


    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // ✅ 2. Get latest salary slip for employee
    @GetMapping("/salary/slip/{employeeId}")
    public ResponseEntity<SalarySlip> getLatestSalarySlip(@PathVariable Long employeeId) {
        return ResponseEntity.ok(financeService.getSalarySlipByEmployeeId(employeeId));
    }
     // ✅ 3. Get all salary slips for an employee
    @GetMapping("/salary/slips/{employeeId}")
    public ResponseEntity<List<SalarySlip>> getAllSalarySlipsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(financeService.getAllSalarySlipsByEmployeeId(employeeId));
    }

}
