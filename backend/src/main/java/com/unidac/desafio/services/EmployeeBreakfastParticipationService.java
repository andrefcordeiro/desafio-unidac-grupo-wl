package com.unidac.desafio.services;

import com.unidac.desafio.dto.EmployeeBreakfastParticipationDTO;
import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.repositories.EmployeeBreakfastParticipationRepository;
import com.unidac.desafio.repositories.EmployeeRepository;
import com.unidac.desafio.services.exceptions.InvalidBreakfastParticipationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeBreakfastParticipationService {

    @Autowired
    private EmployeeBreakfastParticipationRepository employeeBreakfastParticipationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeBreakfastParticipationDTO insert(EmployeeBreakfastParticipationDTO employeeBreakfastParticipationDTO) {
        Employee employee = employeeRepository.findByCPF(employeeBreakfastParticipationDTO.getEmployeeCpf());
        Long breakfastId = employeeBreakfastParticipationDTO.getBreakfastId();

        for (Long foodOptionId : employeeBreakfastParticipationDTO.getFoodOptions()) {

            // verificando se a opção já foi escolhida
            Long employeeId = employeeBreakfastParticipationRepository.
                    foodOptionWasSelected(breakfastId, foodOptionId);

            if (employeeId != null) {
                throw new InvalidBreakfastParticipationException("FoodOption with id " + foodOptionId +
                        " has already been selected by employee with id: " + employeeId +
                        " for breakfast with id: " + breakfastId + ".");
            }

            employeeBreakfastParticipationRepository.insert(breakfastId, employee.getId(), foodOptionId);
        }
        return employeeBreakfastParticipationDTO;
    }
}
