package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Bike;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.service.vehicle.VehicleService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingManagerServiceTest {

    @InjectMocks
    ParkingManagerService parkingManagerService;

    @Mock
    ParkingSlotService parkingSlotService;

    @Mock
    ParkingLotService parkingLotService;

    @Mock
    VehicleService vehicleService;

    @Test
    public void park() throws Exception {
        String regNumber = "DUCATI-01";
        Vehicle bike = new Bike("Ducati Monster", "White", regNumber);
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setSlotId(101);
        when(vehicleService.isVehicleExists(regNumber)).thenReturn(true);
        when(vehicleService.getVehicleByRegNum(regNumber)).thenReturn(bike);
        when(parkingSlotService.findVehicle(bike)).thenReturn(null);
        when(parkingLotService.park(bike)).thenReturn(parkingSlot);
        ParkingSlot slot = parkingManagerService.park(regNumber);
        assertEquals(slot, parkingSlot);
    }

    @Rule public ExpectedException thrown = ExpectedException.none();
    @Test(expected = IllegalArgumentException.class)
    public void park_InvalidRegNum() throws Exception {
        String regNumber = "DUCATI-01";
        Vehicle bike = new Bike("Ducati Monster", "White", regNumber);
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setSlotId(101);
        when(vehicleService.getVehicleInfo(regNumber)).thenThrow(new IllegalArgumentException("Vehicle with reg number : " + regNumber + " is not found"));
        parkingManagerService.park(regNumber);
        thrown.expectMessage("Vehicle with reg number : " + regNumber + " is not found");
    }
}