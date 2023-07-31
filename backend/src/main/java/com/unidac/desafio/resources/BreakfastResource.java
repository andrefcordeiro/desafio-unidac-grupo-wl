package com.unidac.desafio.resources;

import com.unidac.desafio.entities.Breakfast;
import com.unidac.desafio.services.BreakfastService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/breakfast")
public class BreakfastResource {

    @Autowired
    private BreakfastService breakfastService;

    @PostMapping
    public ResponseEntity<Breakfast> insert(@Valid @RequestBody Breakfast obj, BindingResult result) {
        obj = breakfastService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Breakfast>> getAll() {
        List<Breakfast> breakfastList = breakfastService.getAll();
        return ResponseEntity.ok().body(breakfastList);
    }
}
