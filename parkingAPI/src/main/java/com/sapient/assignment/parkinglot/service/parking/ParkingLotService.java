package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.repository.parking.ParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepo parkingLotRepo;

    @Autowired
    private ParkingFloorService parkingFloorService;

    @Autowired
    private ParkingLotService parkingLotService;

    @Value("${parking.lots}")
    private String lotsCount;

    @Value("${lot.floors}")
    private String floorCount;

    @Value("${parkingFloor.rows}")
    private String rowsCount;

    public ParkingLot createParkingLot(final ParkingLot parkingLot) {
        return parkingLotRepo.createParkingLot(parkingLot);
    }

    public ParkingLot getById(Integer parking_lot_id) {
        return parkingLotRepo.getById(parking_lot_id);
    }

    public ParkingSlot park(Vehicle vehicle) {
        List<ParkingLot> parkingLots = parkingLotRepo.getAll();
        for(ParkingLot lot : parkingLots) {
            List<ParkingFloor> floors = parkingFloorService.getAvailableFloors(lot);
            for(ParkingFloor floor : floors) {
                ParkingSlot parkingSlot = parkingFloorService.park(vehicle, floor);
                if(null != parkingSlot && parkingSlot.getVehicle().equals(vehicle)) return parkingSlot;
            }
            if(floors.size() < Integer.parseInt(floorCount)) {
                ParkingFloor parkingFloor = parkingFloorService.createParkingFloor(
                    new ParkingFloor(lot, floors.size() + 1, Integer.parseInt(rowsCount))
                );
                return parkingFloorService.park(vehicle, parkingFloor);
            }
        }
        if(parkingLots.size() < Integer.parseInt(lotsCount)) {
            ParkingLot parkingLot = createParkingLot(new ParkingLot(Integer.parseInt(floorCount)));
            ParkingFloor parkingFloor = parkingFloorService.createParkingFloor(new ParkingFloor(parkingLot, 1, Integer.parseInt(rowsCount)));
            return parkingFloorService.park(vehicle, parkingFloor);
        }
        return null;
    }
}
