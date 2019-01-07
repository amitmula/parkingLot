package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.repository.parking.ParkingFloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingFloorService {

    @Autowired
    private ParkingFloorRepo parkingFloorRepo;

    @Autowired
    private ParkingRowService parkingRowService;

    @Value("${parkingFloor.rows}")
    private String rowsCount;

    public ParkingFloor createParkingFloor(ParkingFloor parkingFloor) {
        return parkingFloorRepo.createParkingFloor(parkingFloor);
    }

    public ParkingFloor getById(Integer parking_floor_id) {
        return parkingFloorRepo.getById(parking_floor_id);
    }

    public ParkingSlot park(Vehicle vehicle, ParkingFloor floor) {
        List<ParkingRow> parkingRows = parkingRowService.getAvailableRows(floor);
        ParkingSlot parkingSlot = parkingRows.stream()
            .map(row -> parkingRowService.park(vehicle, row))
            .sorted()
            .findFirst()
            .orElseGet(null);

        if(null != parkingSlot && parkingSlot.getVehicle().equals(vehicle)) return parkingSlot;

        if(parkingRows.size() < Integer.parseInt(rowsCount)) {
            /*ParkingFloor parkingFloor = createParkingFloor(
                new ParkingFloor(floor.getParkingLot(), parkingRows.size() + 1, Integer.parseInt(rowsCount))
            );*/
            ParkingRow parkingRow = parkingRowService.createParkingRow(
                new ParkingRow(floor, parkingRows.size() + 1)
            );
            return parkingRowService.park(vehicle, parkingRow);
        }
        return null;
    }

    public List<ParkingFloor> getAvailableFloors(ParkingLot lot) {
        return parkingFloorRepo.getAvailableFloor(lot);
    }
}
