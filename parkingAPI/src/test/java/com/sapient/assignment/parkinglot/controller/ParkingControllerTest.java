package com.sapient.assignment.parkinglot.controller;

import com.sapient.assignment.parkinglot.domain.parking.*;
import com.sapient.assignment.parkinglot.domain.vehicle.Car;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.service.parking.ParkingManagerService;
import com.sapient.assignment.parkinglot.service.parking.ParkingSlotService;
import com.sapient.assignment.parkinglot.service.vehicle.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ParkingController.class, secure = false)
public class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingManagerService parkingManagerService;

    @MockBean
    private VehicleService vehicleService;

    @MockBean
    private ParkingSlotService parkingSlotService;

    @Test
    public void getVehicleInfo() throws Exception {
        Vehicle mockVehicle = new Car("Bugatti Veron", "White", "BUGATTI-01");
        String vehicleJSON = "{\n" +
            "    \"vehicleId\": null,\n" +
            "    \"name\": \"Bugatti Veron\",\n" +
            "    \"colour\": \"White\",\n" +
            "    \"regNumber\": \"BUGATTI-01\",\n" +
            "    \"wheels\": 4,\n" +
            "    \"vehicleType\": \"CAR\"\n" +
            "}";
        Mockito.when(vehicleService.getVehicleInfo(Mockito.anyString())).thenReturn(mockVehicle);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/parking/get/vehicleinfo/BUGATTI-01")
            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(vehicleJSON, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getParkingSlot() throws Exception {

        Vehicle mockVehicle = new Car("Bugatti Veron", "White", "BUGATTI-01");

        ParkingSlot mockParkingSlot = new ParkingSlot();
        mockParkingSlot.setSlotId(101);
        mockParkingSlot.setStatus(ParkingStatus.OCCUPIED);
        mockParkingSlot.setSlotNumber(1);
        mockParkingSlot.setSlotSize(SlotSize.MEDIUM);
        ParkingRow parkingRow = new ParkingRow(null, 1);
        parkingRow.setRowId(100);
        mockParkingSlot.setParkingRow(parkingRow);
        Car vehicle = new Car("Bugatti Veron", "White", "BUGATTI-01");
        vehicle.setVehicleId(101);
        mockParkingSlot.setVehicle(vehicle);

        String parkingSlotResponse = "{\n" +
            "    \"slotId\": 101,\n" +
            "    \"slotNumber\": 1,\n" +
            "    \"parkingRow\": {\n" +
            "        \"rowId\": 100,\n" +
            "        \"parkingFloor\": null,\n" +
            "        \"rowNumber\": 1\n" +
            "    },\n" +
            "    \"status\": \"OCCUPIED\",\n" +
            "    \"slotSize\": \"MEDIUM\",\n" +
            "    \"vehicle\": {\n" +
            "        \"vehicleId\": 101,\n" +
            "        \"name\": \"Bugatti Veron\",\n" +
            "        \"colour\": \"White\",\n" +
            "        \"regNumber\": \"BUGATTI-01\",\n" +
            "        \"wheels\": 4,\n" +
            "        \"vehicleType\": \"CAR\"\n" +
            "    }\n" +
            "}";

        Mockito.when(vehicleService.getVehicleInfo(Mockito.anyString())).thenReturn(mockVehicle);
        Mockito.when(parkingManagerService.park(Mockito.anyString())).thenReturn(mockParkingSlot);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/parking/allocate/BUGATTI-01")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(parkingSlotResponse, result.getResponse().getContentAsString(), false);
    }

}