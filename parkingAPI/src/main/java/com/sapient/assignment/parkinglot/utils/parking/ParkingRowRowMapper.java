package com.sapient.assignment.parkinglot.utils.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.service.parking.ParkingFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ParkingRowRowMapper implements RowMapper<ParkingRow> {

    @Autowired
    ParkingFloorService parkingFloorService;

    @Nullable
    @Override
    public ParkingRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParkingRow parkingRow = new ParkingRow();
        parkingRow.setParkingFloor(parkingFloorService.getById(rs.getInt("floor_id")));
        parkingRow.setRowNumber(rs.getInt("row_number"));
        parkingRow.setRowId(rs.getInt("rowId"));
        return parkingRow;
    }
}
