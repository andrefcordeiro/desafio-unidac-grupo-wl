package com.unidac.desafio.services;

import com.unidac.desafio.dto.EmployeeBreakfastParticipationDTO;
import com.unidac.desafio.dto.EmployeeBreakfastParticipationUpdateStatusDTO;
import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.entities.FoodOption;
import com.unidac.desafio.projections.EmployeeBreakfastParticipationProjection;
import com.unidac.desafio.repositories.EmployeeBreakfastParticipationRepository;
import com.unidac.desafio.repositories.EmployeeRepository;
import com.unidac.desafio.services.exceptions.InvalidBreakfastParticipationException;
import com.unidac.desafio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeBreakfastParticipationService {

    @Autowired
    private EmployeeBreakfastParticipationRepository employeeBreakfastParticipationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeBreakfastParticipationDTO insert(EmployeeBreakfastParticipationDTO employeeBreakfastParticipationDTO) {
        Employee employee = employeeRepository.findByCPF(employeeBreakfastParticipationDTO.getEmployeeCpf());

        if (employee == null)
            throw new ResourceNotFoundException("cpf", employeeBreakfastParticipationDTO.getEmployeeCpf());

        Long breakfastId = employeeBreakfastParticipationDTO.getBreakfastId();

        for (Long foodOptionId : employeeBreakfastParticipationDTO.getFoodOptionsIds()) {

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

    public List<EmployeeBreakfastParticipationDTO> getAllByBreakfastId(Long breakfastId) {
        List<EmployeeBreakfastParticipationProjection> projections =
                employeeBreakfastParticipationRepository.getAllByBreakfastId(breakfastId);
        return projections.stream().map(EmployeeBreakfastParticipationDTO::new).toList();
    }

    public void updateFoodWasBroughtStatus(EmployeeBreakfastParticipationUpdateStatusDTO employeeBreakfastParticipationDTO) {
        employeeBreakfastParticipationRepository.
                updateFoodWasBroughtStatus(employeeBreakfastParticipationDTO.getBreakfastId(), employeeBreakfastParticipationDTO.getEmployeeId(),
                        employeeBreakfastParticipationDTO.getFoodOptionId(), employeeBreakfastParticipationDTO.getFoodOptionWasBrought());
    }
}
