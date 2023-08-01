package com.unidac.desafio.resources;

import com.unidac.desafio.dto.EmployeeBreakfastParticipationDTO;
import com.unidac.desafio.dto.EmployeeBreakfastParticipationUpdateStatusDTO;
import com.unidac.desafio.services.EmployeeBreakfastParticipationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
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

    @GetMapping
    public ResponseEntity<List<EmployeeBreakfastParticipationDTO>> getAll(@PathVariable("id") Long breakfastId) {
        List<EmployeeBreakfastParticipationDTO> breakfastParticipations =
                employeeBreakfastParticipationService.getAllByBreakfastId(breakfastId);
        return ResponseEntity.ok().body(breakfastParticipations);
    }

    @PostMapping(path = "/{employeeId}/foodOptions/{foodOptionId}/update-food-was-brought-status")
    public ResponseEntity updateFoodWasBroughtStatus(@Valid @RequestBody EmployeeBreakfastParticipationUpdateStatusDTO obj,
                                                     @PathVariable("id") Long breakfastId,
                                                     @PathVariable("employeeId") Long employeeId,
                                                     @PathVariable("foodOptionId") Long foodOptionId) {
        obj.setBreakfastId(breakfastId);
        obj.setEmployeeId(employeeId);
        obj.setFoodOptionId(foodOptionId);
        employeeBreakfastParticipationService.updateFoodWasBroughtStatus(obj);
        return ResponseEntity.noContent().build();
    }
}
