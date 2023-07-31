package com.unidac.desafio.entities;

import java.io.Serializable;
import java.util.Objects;

import com.unidac.desafio.entities.pk.EmployeeBreakfastParticipationPK;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_employee_breakfast_participation")
public class EmployeeBreakfastParticipation implements Serializable {

    @EmbeddedId
    private EmployeeBreakfastParticipationPK id = new EmployeeBreakfastParticipationPK();

    private Boolean foodOptionWasBrought;

    public EmployeeBreakfastParticipation() {
    }

    public EmployeeBreakfastParticipation(Employee employee, Breakfast breakfast, FoodOption foodOption) {
        this.id.setEmployee(employee);
        this.id.setBreakfast(breakfast);
        this.id.setFoodOption(foodOption);
    }

    public EmployeeBreakfastParticipationPK getId() {
        return id;
    }

    public void setId(EmployeeBreakfastParticipationPK id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return id.getEmployee();
    }

    public Breakfast getBreakfast() {
        return id.getBreakfast();
    }

    public Boolean getFoodOptionWasBrought() {
        return foodOptionWasBrought;
    }

    public void setFoodOptionWasBrought(Boolean foodOptionWasBrought) {
        this.foodOptionWasBrought = foodOptionWasBrought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeBreakfastParticipation that = (EmployeeBreakfastParticipation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
