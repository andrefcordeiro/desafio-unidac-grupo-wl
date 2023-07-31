package com.unidac.desafio.dto;

import java.io.Serializable;
import java.util.Set;

public class EmployeeBreakfastParticipationDTO implements Serializable {

    private String employeeCpf;

    private Long breakfastId;

    Set<Long> foodOptions;

    public EmployeeBreakfastParticipationDTO() {
    }

    public EmployeeBreakfastParticipationDTO(String employeeCpf, Long breakfastId, Set<Long> foodOptions) {
        this.employeeCpf = employeeCpf;
        this.breakfastId = breakfastId;
        this.foodOptions = foodOptions;
    }

    public String getEmployeeCpf() {
        return employeeCpf;
    }

    public void setEmployeeCpf(String employeeCpf) {
        this.employeeCpf = employeeCpf;
    }

    public Long getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(Long breakfastId) {
        this.breakfastId = breakfastId;
    }

    public Set<Long> getFoodOptions() {
        return foodOptions;
    }

    public void addFoodOption(Long foodOptionId) {
        this.foodOptions.add(foodOptionId);
    }
}
