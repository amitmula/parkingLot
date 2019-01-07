package com.sapient.assignment.parkinglot.domain.vehicle;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;

public interface Vehicle {
    public void setVehicleId(Integer id);
    public Integer getVehicleId();
    public String getColour();
    public String getName();
    public String getRegNumber();
    public VehicleType getVehicleType();
    public int getWheels();
    public boolean canFitInSpot(ParkingSlot slot);
}
