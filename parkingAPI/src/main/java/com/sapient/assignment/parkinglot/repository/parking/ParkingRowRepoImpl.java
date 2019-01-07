package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.utils.parking.ParkingRowRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class ParkingRowRepoImpl implements ParkingRowRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ParkingRowRowMapper parkingRowRowMapper;

    @Override
    public ParkingRow createParkingRow(ParkingRow parkingRow) {
        final String sql = "insert into public.parking_row(floor_id, row_number) values(?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingRow.getParkingFloor().getFloorId());
            ps.setInt(2, parkingRow.getRowNumber());
            return ps;
        }, holder);
        parkingRow.setRowId(holder.getKey().intValue());
        return parkingRow;
    }

    @Override
    public ParkingRow getById(Integer parking_row_id) {
        String sql = "select * from public.parking_row where rowId = ?";
        ParkingRow parkingRow = (ParkingRow)jdbcTemplate.queryForObject(
                sql, new Object[] { parking_row_id }, parkingRowRowMapper);
        return parkingRow;
    }

    @Override
    public List<ParkingRow> getAvailableRows(ParkingFloor floor) {
        String sql = "select * from public.parking_row where floor_id = ?";
        List<ParkingRow> parkingRows = jdbcTemplate.query(sql, new Object[] { floor.getFloorId() }, new BeanPropertyRowMapper(ParkingRow.class));
        return parkingRows;
    }

    @Override
    public ParkingSlot parkVehicle(Vehicle vehicle) {
        return null;
    }
}
