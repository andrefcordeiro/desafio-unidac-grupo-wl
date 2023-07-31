package com.unidac.desafio.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> insert(@Valid @RequestBody Employee obj, BindingResult result) {
        obj = employeeService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
