package com.sapient.assignment.parkinglot.domain.vehicle;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;

public abstract class AbstractVehicle implements Vehicle {

    private Integer vehicleId;
    private String name;
    private String colour;
    private String regNumber;
    private VehicleType type;

    public AbstractVehicle(String name, String colour, String regNumber, Integer type) {
        this.name = name;
        this.colour = colour;
        this.regNumber = regNumber;
        this.type = VehicleType.getByVal(type);
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public String getRegNumber() {
        return regNumber;
    }

    @Override
    public VehicleType getVehicleType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean canFitInSpot(ParkingSlot slot) {
        return slot.getSlotSize().isVehicleParkingPossible(this);
    }
}
