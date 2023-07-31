package com.unidac.desafio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.repositories.EmployeeRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // employee
        // Employee e = new Employee(null, "Fulano de Tal", "124.786.171-09");
        // employeeRepository.insert(e.getName(), e.getCpf());

        System.out.println("Finalizado!!!");
    }
}