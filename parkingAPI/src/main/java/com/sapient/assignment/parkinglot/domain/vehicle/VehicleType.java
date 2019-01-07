package com.sapient.assignment.parkinglot.domain.vehicle;

public enum VehicleType {

    BIKE(1), CAR(2), BUS(3);

    private final int val;

    VehicleType(final int val) {
        this.val = val;
    }

    public static VehicleType getByVal(int val) {
        for(VehicleType type: VehicleType.values()) {
            if(type.getVal() == val) return type;
        }
        throw new IllegalArgumentException(val + " isn't a valid vehicle type value");
    }

    public int getVal() { return val; }
}
