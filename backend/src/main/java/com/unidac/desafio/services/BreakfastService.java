package com.unidac.desafio.services;

import com.unidac.desafio.entities.Breakfast;
import com.unidac.desafio.repositories.BreakfastRepository;
import com.unidac.desafio.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BreakfastService {

    @Autowired
    private BreakfastRepository breakfastRepository;

    public Breakfast insert(Breakfast breakfast) {
        Breakfast obj = breakfastRepository.findByDate(breakfast.getDate());
        if (obj != null) {
            throw new ResourceAlreadyExistsException("date", obj.getDate());
        }
        breakfastRepository.insert(breakfast.getDate());
        return breakfastRepository.findByDate(breakfast.getDate());
    }

    @Transactional(readOnly = true)
    public List<Breakfast> getAll() {
        return breakfastRepository.getAll();
    }
}
