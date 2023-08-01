package com.unidac.desafio.config;

import com.unidac.desafio.entities.Breakfast;
import com.unidac.desafio.repositories.BreakfastRepository;
import com.unidac.desafio.repositories.EmployeeBreakfastParticipationRepository;
import com.unidac.desafio.repositories.FoodOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unidac.desafio.entities.Employee;
import com.unidac.desafio.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BreakfastRepository breakfastRepository;

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    @Autowired
    private EmployeeBreakfastParticipationRepository employeeBreakfastParticipationRepository;

    @Override
    public void run(String... args) throws Exception {
        // employee
        Employee e = new Employee(null, "Fulano de Tal", "124.786.171-09");
        employeeRepository.insert(e.getName(), e.getCpf());

        e = new Employee(null, "Joao", "732.442.160-13");
        employeeRepository.insert(e.getName(), e.getCpf());

        // braekfast
        Breakfast bf1 = new Breakfast(null, LocalDate.of(2023, 8, 1));
        Breakfast bf2 = new Breakfast(null, LocalDate.of(2023, 10, 13));
        breakfastRepository.saveAll(List.of(bf1, bf2));

        // food options
        String[] foodOptions = {
                "Pão de forma", "Pão francês", "Pão integral", "Croissant",
                "Bolo de cenoura", "Bolo de chocolate", "Bolo de laranja", "Bolo de milho", "Bolo de coco",
                "Maçã", "Banana", "Laranja", "Morango", "Mamão", "Uva",
                "Suco de laranja", "Suco de maracujá", "Suco de abacaxi", "Suco de acerola", "Suco de manga", "Suco de limão",
                "Café", "Leite", "Chá",
                "Queijo prato", "Queijo muçarela", "Queijo branco", "Queijo coalho",
                "Presunto", "Peito de peru", "Salame", "Mortadela",
                "Iogurte natural", "Iogurte de frutas",
                "Granola", "Cereais matinais", "Aveia",
                "Manteiga com sal", "Manteiga sem sal", "Geleia de morango", "Geleia de damasco", "Geleia de frutas vermelhas",
                "Ovos mexidos", "Ovos cozidos",
                "Alface", "Tomate", "Cenoura ralada", "Peito de frango desfiado"
        };
        for (String foodName : foodOptions) {
            foodOptionRepository.insert(foodName);
        }

        // employee breakfast participation
        employeeBreakfastParticipationRepository.insert(1L, 1L, 1L);
        employeeBreakfastParticipationRepository.insert(1L, 1L, 2L);

        employeeBreakfastParticipationRepository.insert(1L, 2L, 3L);


        System.out.println("Finalizado!!!");
    }
}