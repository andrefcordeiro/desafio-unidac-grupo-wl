package com.unidac.desafio.dto;


public class FoodOptionDTO {

    private Long id;

    private String foodName;

    private Boolean foodOptionWasBrought;

    public FoodOptionDTO() {
    }

    public FoodOptionDTO(Long id, String foodName, Boolean foodOptionWasBrought) {
        this.id = id;
        this.foodName = foodName;
        this.foodOptionWasBrought = foodOptionWasBrought;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Boolean getFoodOptionWasBrought() {
        return foodOptionWasBrought;
    }

    public void setFoodOptionWasBrought(Boolean foodOptionWasBrought) {
        this.foodOptionWasBrought = foodOptionWasBrought;
    }
}
