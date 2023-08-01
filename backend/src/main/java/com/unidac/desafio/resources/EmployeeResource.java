package com.unidac.desafio.resources;

import java.net.URI;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.services.EmployeeService;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> insert(@Valid @RequestBody Employee obj) {
        obj = employeeService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
