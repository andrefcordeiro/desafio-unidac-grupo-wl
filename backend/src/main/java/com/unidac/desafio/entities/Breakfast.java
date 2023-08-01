package com.unidac.desafio.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_breakfast")
public class Breakfast implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "date should not be null")
    private LocalDate date;

    public Breakfast() {
    }

    public Breakfast(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breakfast breakfast = (Breakfast) o;
        return Objects.equals(id, breakfast.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
