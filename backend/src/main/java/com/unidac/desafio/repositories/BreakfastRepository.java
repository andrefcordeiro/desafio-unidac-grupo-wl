package com.unidac.desafio.repositories;

import com.unidac.desafio.entities.Breakfast;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BreakfastRepository extends JpaRepository<Breakfast, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tb_breakfast (date) VALUES (:date)", nativeQuery = true)
    int insert(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM tb_breakfast WHERE date = :date", nativeQuery = true)
    Breakfast findByDate(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM tb_breakfast ORDER BY date DESC", nativeQuery = true)
    List<Breakfast> getAll();
}
