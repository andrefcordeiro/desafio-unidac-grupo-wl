package com.unidac.desafio.services;

import com.unidac.desafio.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee insert(Employee employee) {
        Employee obj = employeeRepository.findByCPF(employee.getCpf());
        if (obj != null) {
            throw new ResourceAlreadyExistsException("cpf", obj.getCpf());
        }
        employeeRepository.insert(employee.getName(), employee.getCpf());
        return employeeRepository.findByCPF(employee.getCpf());
    }
}
