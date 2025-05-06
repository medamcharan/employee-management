package com.charan.employeemanagement.controller;

import com.charan.employeemanagement.model.Employee;
import com.charan.employeemanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/employees")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return adminService.getAllEmployees();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return adminService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return adminService.updateEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
    return adminService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        adminService.deleteEmployee(id);
    }
}
