package com.charan.employeemanagement.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "salary_slip",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "dateIssued"})
)
public class SalarySlip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;
    private double grossSalary;
    private double taxDeduction;
    private double netSalary;
    private double bonus; // Added bonus field
    private String bonusAdded; // Added bonusAdded message field

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate dateIssued;

    public SalarySlip() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(double taxDeduction) {
        this.taxDeduction = taxDeduction;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getBonusAdded() {
        return bonusAdded;
    }

    public void setBonusAdded(String bonusAdded) {
        this.bonusAdded = bonusAdded;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    @PrePersist
    protected void onCreate() {
        dateIssued = LocalDate.now();
    }
}
