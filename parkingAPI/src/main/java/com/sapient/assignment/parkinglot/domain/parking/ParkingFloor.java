package com.sapient.assignment.parkinglot.domain.parking;

public class ParkingFloor {

    private Integer floorId;
    private ParkingLot parkingLot;
    private Integer floorNumber;
    private Integer rowsCount;

    public ParkingFloor() {}

    public ParkingFloor(ParkingLot parkingLot, Integer floorNumber, Integer rowsCount) {
        this.parkingLot = parkingLot;
        this.floorNumber = floorNumber;
        this.rowsCount = rowsCount;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(Integer rowsCount) {
        this.rowsCount = rowsCount;
    }
}
