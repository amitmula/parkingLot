package com.sapient.assignment.parkinglot.domain.vehicle;

public abstract class FourWheeler extends AbstractVehicle {

    private int wheels;

    public FourWheeler(String name, String colour, String regNumber, VehicleType vehicleType) {
        super(name, colour, regNumber, vehicleType.getVal());
        this.wheels = 4;
    }

    @Override
    public int getWheels() {
        return this.wheels;
    }
}
