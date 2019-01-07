package com.sapient.assignment.parkinglot.domain.vehicle;

public abstract class TwoWheeler extends AbstractVehicle{

    private int wheels;

    TwoWheeler(String name, String colour, String regNumber, VehicleType vehicleType) {
        super(name, colour, regNumber, vehicleType.getVal());
        this.wheels = 2;
    }

    @Override
    public int getWheels() {
        return this.wheels;
    }
}
