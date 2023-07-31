package com.unidac.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unidac.desafio.entities.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tb_employee (name, cpf) VALUES (:name, :cpf)", nativeQuery = true)
    int insert(@Param("name") String name, @Param("cpf") String cpf);

    @Query(value = "SELECT * FROM tb_employee WHERE cpf = :cpf", nativeQuery = true)
    Employee findByCPF(@Param("cpf") String cpf);
}
