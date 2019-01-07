package com.sapient.assignment.parkinglot.utils.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingStatus;
import com.sapient.assignment.parkinglot.domain.parking.SlotSize;
import com.sapient.assignment.parkinglot.service.parking.ParkingRowService;
import com.sapient.assignment.parkinglot.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ParkingSlotRowMapper implements RowMapper<ParkingSlot> {

    @Autowired
    ParkingRowService rowService;

    @Autowired
    VehicleService vehicleService;

    @Nullable
    @Override
    public ParkingSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setSlotId(rs.getInt("slotId"));
        parkingSlot.setSlotSize(SlotSize.findbyVal(rs.getInt("slot_size")));
        parkingSlot.setSlotNumber(rs.getInt("slot_number"));
        parkingSlot.setStatus(ParkingStatus.getByVal(rs.getInt("status")));
        int row_id = rs.getInt("row_id");
        parkingSlot.setParkingRow(rowService.getRowById(row_id));
        int vehicle_id = rs.getInt("vehicle_id");
        parkingSlot.setVehicle(vehicleService.getVehicleById(vehicle_id));
        return parkingSlot;
    }
}
