package com.unidac.desafio.repositories;

import com.unidac.desafio.entities.FoodOption;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodOptionRepository extends JpaRepository<FoodOption, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tb_food_option (food_name) VALUES (:foodName)", nativeQuery = true)
    int insert(@Param("foodName") String foodName);

    @Query(value = "SELECT * FROM tb_food_option ORDER BY id ASC", nativeQuery = true)
    List<FoodOption> getAll();
}
