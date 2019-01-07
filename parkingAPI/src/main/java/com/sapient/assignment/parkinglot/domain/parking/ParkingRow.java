package com.sapient.assignment.parkinglot.domain.parking;

public class ParkingRow {

    private Integer rowId;
    private ParkingFloor parkingFloor;
    private Integer rowNumber;

    public ParkingRow() {}

    public ParkingRow(ParkingFloor parkingFloor, Integer rowNumber) {
        this.parkingFloor = parkingFloor;
        this.rowNumber = rowNumber;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
}
