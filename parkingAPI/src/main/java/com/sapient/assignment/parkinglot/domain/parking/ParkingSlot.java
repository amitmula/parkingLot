package com.sapient.assignment.parkinglot.domain.parking;

import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.utils.parking.SlotSizeComparator;

public class ParkingSlot implements Comparable{

    private Integer slotId;
    private Integer slotNumber;
    private ParkingRow parkingRow;
    private ParkingStatus status;
    private SlotSize slotSize;
    private Vehicle vehicle;

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public void park(Vehicle vehicle) {
        this.status = ParkingStatus.OCCUPIED;
        this.vehicle = vehicle;
    }

    public void unPark() {
        this.status = ParkingStatus.AVAILABLE;
        this.vehicle = null;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public ParkingRow getParkingRow() {
        return parkingRow;
    }

    public void setParkingRow(ParkingRow parkingRow) {
        this.parkingRow = parkingRow;
    }

    public SlotSize getSlotSize() {
        return slotSize;
    }

    public void setSlotSize(SlotSize slotSize) {
        this.slotSize = slotSize;
    }

    public ParkingStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingStatus status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        return (((ParkingSlot) o).slotNumber == this.slotNumber);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.slotNumber;
        return hash;
    }

    @Override
    public int compareTo(Object obj) {
        ParkingSlot that = (ParkingSlot) obj;
        SlotSizeComparator comparator = new SlotSizeComparator();
        return comparator.compare(this.slotSize, that.slotSize);
    }
}
