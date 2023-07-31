package com.unidac.desafio.config;

import com.unidac.desafio.entities.Breakfast;
import com.unidac.desafio.repositories.BreakfastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BreakfastRepository breakfastRepository;

    @Override
    public void run(String... args) throws Exception {
        // employee
        Employee e = new Employee(null, "Fulano de Tal", "124.786.171-09");
        employeeRepository.insert(e.getName(), e.getCpf());

        // braekfast
        Breakfast bf1 = new Breakfast(null, LocalDate.of(2023, 8, 1));
        Breakfast bf2 = new Breakfast(null, LocalDate.of(2023, 10, 13));
        breakfastRepository.saveAll(List.of(bf1, bf2));

        System.out.println("Finalizado!!!");
    }
}