package com.sapient.assignment.parkinglot.utils.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.service.parking.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ParkingFloorRowMapper implements RowMapper<ParkingFloor> {

    @Autowired
    ParkingLotService parkingLotService;

    @Nullable
    @Override
    public ParkingFloor mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParkingFloor parkingFloor = new ParkingFloor();
        int parking_lot_id = rs.getInt("parking_lot_id");
        parkingFloor.setParkingLot(parkingLotService.getById(parking_lot_id));
        parkingFloor.setFloorId(rs.getInt("floorId"));
        parkingFloor.setFloorNumber(rs.getInt("floor_number"));
        parkingFloor.setRowsCount(rs.getInt("rows_count"));
        return parkingFloor;
    }
}
