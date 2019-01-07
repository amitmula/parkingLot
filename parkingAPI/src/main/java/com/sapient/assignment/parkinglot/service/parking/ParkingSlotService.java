package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.parking.SlotSize;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.repository.parking.ParkingSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepo parkingSlotRepo;

    public ParkingSlot createParkingSlot(ParkingSlot parkingSlot) {
        return parkingSlotRepo.createParkingSlot(parkingSlot);
    }

    public List<ParkingSlot> getAllSlots(ParkingRow row) {
        return parkingSlotRepo.getAllSlots(row);
    }

    public ParkingSlot park(Vehicle vehicle, ParkingRow row) {
        return parkingSlotRepo.park(vehicle, row);
    }

    public Integer getRowSlotsCountBySize(SlotSize slotSize, ParkingRow row) {
        return parkingSlotRepo.getRowSlotsCountBySize(slotSize, row);
    }

    public ParkingSlot findVehicle(Vehicle vehicle) {
        return parkingSlotRepo.findVehicle(vehicle);
    }

    public List<ParkingSlot> findAll() {
        return parkingSlotRepo.findAll();
    }
}
