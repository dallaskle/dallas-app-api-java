package com.mongodb.starter.example.dtos;

public record AverageAgeDTO(double averageAge) {

    public AverageAgeDTO(double averageAge) {
        this.averageAge = averageAge;
    }
}
