package com.unidac.desafio.dto;


import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class EmployeeBreakfastParticipationUpdateStatusDTO implements Serializable {
    private Long breakfastId;

    private Long employeeId;

    private Long foodOptionId;

    @NotNull(message = "foodOptionWasBrought should not be null")
    private Boolean foodOptionWasBrought;

    public EmployeeBreakfastParticipationUpdateStatusDTO() {
    }

    public Long getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(Long breakfastId) {
        this.breakfastId = breakfastId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getFoodOptionId() {
        return foodOptionId;
    }

    public void setFoodOptionId(Long foodOptionId) {
        this.foodOptionId = foodOptionId;
    }

    public Boolean getFoodOptionWasBrought() {
        return foodOptionWasBrought;
    }

    public void setFoodOptionWasBrought(Boolean foodOptionWasBrought) {
        this.foodOptionWasBrought = foodOptionWasBrought;
    }
}
