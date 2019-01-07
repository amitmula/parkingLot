package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Bus;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.repository.parking.ParkingFloorRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingFloorServiceTest {

    @InjectMocks
    ParkingFloorService parkingFloorService;

    @Mock
    ParkingFloorRepo parkingFloorRepo;

    @Mock
    ParkingRowService parkingRowService;

    @Test
    public void createParkingFloor() throws Exception {
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setFloorNumber(1);
        parkingFloor.setRowsCount(10);
        parkingFloor.setFloorId(101);
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.setLotId(101);
        parkingFloor.setParkingLot(parkingLot);
        when(parkingFloorRepo.createParkingFloor(parkingFloor)).thenReturn(parkingFloor);
        ParkingFloor result = parkingFloorService.createParkingFloor(parkingFloor);
        assertEquals(result.getFloorId(), new Integer(101));
    }

    @Test
    public void getById() throws Exception {
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setFloorId(101);
        when(parkingFloorRepo.getById(101)).thenReturn(parkingFloor);
        ParkingFloor result = parkingFloorService.getById(101);
        assertEquals(result, parkingFloor);
    }

    @Test
    public void park() throws Exception {
        Vehicle bus = new Bus("Super-Bus", "White", "SUPER-01");
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setSlotId(101);
        parkingSlot.setVehicle(bus);
        ParkingFloor parkingFloor = new ParkingFloor(new ParkingLot(3), 1, 10);
        List<ParkingRow> parkingRows = new ArrayList();
        parkingRows.add(new ParkingRow(parkingFloor, 1));
        when(parkingFloorRepo.parkVehicle(bus)).thenReturn(parkingSlot);
        when(parkingRowService.getAvailableRows(parkingFloor)).thenReturn(parkingRows);
        when(parkingRowService.park(bus, parkingRows.get(0))).thenReturn(parkingSlot);
        ParkingSlot slot = parkingFloorService.park(bus, parkingFloor);
        assertEquals(parkingSlot, slot);
    }

}