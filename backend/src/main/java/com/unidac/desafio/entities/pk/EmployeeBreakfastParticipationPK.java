package com.unidac.desafio.entities.pk;

import java.io.Serializable;

import com.unidac.desafio.entities.Breakfast;
import com.unidac.desafio.entities.Employee;

import com.unidac.desafio.entities.FoodOption;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class EmployeeBreakfastParticipationPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "breakfast_id")
    private Breakfast breakfast;

    @ManyToOne
    @JoinColumn(name = "food_option_id")
    private FoodOption foodOption;

    public EmployeeBreakfastParticipationPK() {
    }

    public EmployeeBreakfastParticipationPK(Employee employee, Breakfast breakfast, FoodOption foodOption) {
        this.employee = employee;
        this.breakfast = breakfast;
        this.foodOption = foodOption;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Breakfast getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    public FoodOption getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(FoodOption foodOption) {
        this.foodOption = foodOption;
    }
}
