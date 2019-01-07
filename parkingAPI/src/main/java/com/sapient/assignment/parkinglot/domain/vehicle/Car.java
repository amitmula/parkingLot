package com.sapient.assignment.parkinglot.domain.vehicle;

public class Car extends FourWheeler {

    public Car(String name, String colour, String regNumber) {
        super(name, colour, regNumber, VehicleType.CAR);
    }
}
