package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.parking.SlotSize;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParkingSlotRepo {
    ParkingSlot createParkingSlot(ParkingSlot parkingSlot);
    ParkingSlot getById(Integer parking_slot_id);
    ParkingSlot park(Vehicle vehicle, ParkingRow row);
    List<ParkingSlot> findAll();
    List<ParkingSlot> getAllSlots(ParkingRow row);
    Integer getRowSlotsCountBySize(SlotSize slotSize, ParkingRow row);
    ParkingSlot findVehicle(Vehicle vehicle);
}
