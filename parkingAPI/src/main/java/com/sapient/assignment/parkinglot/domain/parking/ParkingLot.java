package com.sapient.assignment.parkinglot.domain.parking;

public class ParkingLot {

    private Integer lotId;
    private Integer floorCount;

    public ParkingLot() {}

    public ParkingLot(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public Integer getLotId() {
        return lotId;
    }

    public void setLotId(Integer lotId) {
        this.lotId = lotId;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }
}
