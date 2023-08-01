package com.unidac.desafio.repositories;

import com.unidac.desafio.entities.EmployeeBreakfastParticipation;
import com.unidac.desafio.projections.EmployeeBreakfastParticipationProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    @Query(value = """
            SELECT ebp.breakfast_id AS breakfastId, b.date AS breakfastDate, ebp.employee_id AS employeeId, 
                e.name AS employeeName, e.cpf AS employeeCpf,
                listagg(TO_CHAR(fo.id)) AS foodOptions, listagg(fo.food_name) AS foodOptionsNames
            FROM tb_employee_breakfast_participation AS ebp
            JOIN tb_breakfast AS b ON ebp.breakfast_id = b.id
            JOIN tb_employee AS e ON ebp.employee_id = e.id
            JOIN tb_food_option AS fo ON ebp.food_option_id = fo.id
            WHERE ebp.breakfast_id = 1
            GROUP BY ebp.breakfast_id, ebp.employee_id, e.name;
            """, nativeQuery = true)
    List<EmployeeBreakfastParticipationProjection> getAllByBreakfastId(@Param("breakfastId") Long breakfastId);
}
