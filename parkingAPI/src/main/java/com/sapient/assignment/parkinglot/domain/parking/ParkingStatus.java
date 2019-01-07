package com.sapient.assignment.parkinglot.domain.parking;

public enum ParkingStatus {
    AVAILABLE(1),
    OCCUPIED(2);

    private final int val;

    ParkingStatus(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ParkingStatus getByVal(int val) {
        for(ParkingStatus status: ParkingStatus.values()) {
            if(status.getVal() == val) return status;
        }
        throw new IllegalArgumentException();
    }
}
