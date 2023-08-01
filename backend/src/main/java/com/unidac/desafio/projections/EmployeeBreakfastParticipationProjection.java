package com.unidac.desafio.projections;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public interface EmployeeBreakfastParticipationProjection {
    Long getBreakfastId();

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate getBreakfastDate();

    Long getEmployeeId();

    String getEmployeeName();

    String getEmployeeCpf();

    String getFoodOptions();

    String getFoodOptionsNames();
}
