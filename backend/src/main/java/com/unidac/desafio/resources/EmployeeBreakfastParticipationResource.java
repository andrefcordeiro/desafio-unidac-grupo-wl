package com.unidac.desafio.resources;

import com.unidac.desafio.dto.EmployeeBreakfastParticipationDTO;
import com.unidac.desafio.services.EmployeeBreakfastParticipationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Validated
@RequestMapping(value = "/breakfast/{id}/participations")
public class EmployeeBreakfastParticipationResource {

    @Autowired
    private EmployeeBreakfastParticipationService employeeBreakfastParticipationService;

    @PostMapping
    public ResponseEntity<EmployeeBreakfastParticipationDTO> insert(@Valid @RequestBody EmployeeBreakfastParticipationDTO obj,
                                                                    @PathVariable("id") Long breakfastId, BindingResult result) {
        obj.setBreakfastId(breakfastId);
        obj = employeeBreakfastParticipationService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/breakfastId={breakfastId}&employeeCpf={employeeCpf}")
                .buildAndExpand(obj.getBreakfastId(), obj.getEmployeeCpf())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
