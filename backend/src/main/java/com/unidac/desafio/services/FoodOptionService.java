package com.unidac.desafio.services;

import com.unidac.desafio.entities.FoodOption;
import com.unidac.desafio.repositories.FoodOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodOptionService {

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    @Transactional(readOnly = true)
    public List<FoodOption> getAll() {
        return foodOptionRepository.getAll();
    }
}
