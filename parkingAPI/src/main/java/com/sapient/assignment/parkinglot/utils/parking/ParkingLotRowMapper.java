package com.sapient.assignment.parkinglot.utils.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ParkingLotRowMapper implements RowMapper<ParkingLot> {

    @Nullable
    @Override
    public ParkingLot mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setLotId(rs.getInt("lotId"));
        parkingLot.setFloorCount(rs.getInt("floor_count"));
        return parkingLot;
    }
}
