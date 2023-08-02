package com.unidac.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unidac.desafio.entities.FoodOption;
import com.unidac.desafio.projections.EmployeeBreakfastParticipationProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EmployeeBreakfastParticipationDTO implements Serializable {

    private Long employeeId;

    @NotBlank(message = "employeeCpf should not be empty")
    private String employeeCpf;

    private String employeeName;

    private Long breakfastId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate breakfastDate;

    private Set<FoodOptionDTO> foodOptions = new HashSet<>();

    @NotEmpty(message = "foodOptionsIds should not be empty")
    private Set<Long> foodOptionsIds = new HashSet<>();

    public EmployeeBreakfastParticipationDTO() {
    }

    public EmployeeBreakfastParticipationDTO(EmployeeBreakfastParticipationProjection projection) {
        this.breakfastId = projection.getBreakfastId();
        this.breakfastDate = projection.getBreakfastDate();
        this.employeeId = projection.getEmployeeId();
        this.employeeCpf = projection.getEmployeeCpf();
        this.employeeName = projection.getEmployeeName();

        String[] foodOptionsId = projection.getFoodOptions().split(",");
        String[] foodOptionsNames = projection.getFoodOptionsNames().split(",");
        String[] foodOptionsWereBroughtStatuses = projection.getFoodOptionsWereBroughtStatuses().split(",");

        for (int i = 0; i < foodOptionsNames.length; i++) {
            this.foodOptions.add(new FoodOptionDTO(Long.parseLong(foodOptionsId[i]), foodOptionsNames[i],
                    Boolean.valueOf(foodOptionsWereBroughtStatuses[i])));
        }
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCpf() {
        return employeeCpf;
    }

    public void setEmployeeCpf(String employeeCpf) {
        this.employeeCpf = employeeCpf;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(Long breakfastId) {
        this.breakfastId = breakfastId;
    }

    public LocalDate getBreakfastDate() {
        return breakfastDate;
    }

    public void setBreakfastDate(LocalDate breakfastDate) {
        this.breakfastDate = breakfastDate;
    }

    public Set<FoodOptionDTO> getFoodOptions() {
        return foodOptions;
    }

    public void addFoodOption(FoodOptionDTO foodOption) {
        this.foodOptions.add(foodOption);
    }

    public Set<Long> getFoodOptionsIds() {
        return foodOptionsIds;
    }

    public void setFoodOptionsIds(Set<Long> foodOptionsIds) {
        this.foodOptionsIds = foodOptionsIds;
    }
}
