package com.mongodb.starter.example.dtos;

import com.mongodb.starter.example.models.CarEntity;

public record CarDTO(String brand, String model, Float maxSpeedKmH) {

    public CarDTO(CarEntity c) {
        this(c.getBrand(), c.getModel(), c.getMaxSpeedKmH());
    }

    public CarEntity toCarEntity() {
        return new CarEntity(brand, model, maxSpeedKmH);
    }
}
