package com.unidac.desafio.repositories;

import com.unidac.desafio.entities.EmployeeBreakfastParticipation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeBreakfastParticipationRepository extends JpaRepository<EmployeeBreakfastParticipation, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tb_employee_breakfast_participation (breakfast_id, employee_id, food_option_id) " +
            "VALUES (:breakfastId, :employeeId, :foodOptionId)", nativeQuery = true)
    int insert(@Param("breakfastId") Long breakfastId, @Param("employeeId") Long employeeId,
               @Param("foodOptionId") Long foodOptionId);

    @Query(value = "SELECT employee_id FROM tb_employee_breakfast_participation " +
            "WHERE breakfast_id = :breakfastId AND food_option_id = :foodOptionId", nativeQuery = true)
    Long foodOptionWasSelected(@Param("breakfastId") Long id, @Param("foodOptionId") Long foodOptionId);
}
