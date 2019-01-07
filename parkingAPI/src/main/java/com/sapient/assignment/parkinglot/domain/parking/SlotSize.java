package com.sapient.assignment.parkinglot.domain.parking;

import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.domain.vehicle.VehicleType;

import java.util.List;

import static com.sapient.assignment.parkinglot.domain.vehicle.VehicleType.BIKE;
import static com.sapient.assignment.parkinglot.domain.vehicle.VehicleType.BUS;
import static com.sapient.assignment.parkinglot.domain.vehicle.VehicleType.CAR;
import static java.util.Arrays.asList;

public enum SlotSize {

    SMALL(asList(BIKE), 1),
    MEDIUM(asList(BIKE, CAR), 2),
    LARGE(asList(BIKE, CAR, BUS), 3);

    private final List<VehicleType> vehicleTypesAllowed;
    private final Integer val;

    SlotSize(List<VehicleType> vehicleTypes, Integer val) {
        this.vehicleTypesAllowed = vehicleTypes;
        this.val = val;
    }

    public static SlotSize findbyVal(int val) {
        for(SlotSize size: SlotSize.values()) {
            if(size.getVal() == val)
                return size;
        }
        throw new IllegalArgumentException();
    }

    public boolean isVehicleParkingPossible(Vehicle vehicle) {
        return vehicleTypesAllowed.contains(vehicle.getVehicleType());
    }

    public SlotSize getSlotSize(){
        return this;
    }

    public Integer getVal() {
        return val;
    }
}
