package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.utils.parking.ParkingFloorRowMapper;
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
public class ParkingFloorRepoImpl implements ParkingFloorRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ParkingFloorRowMapper parkingFloorRowMapper;

    @Override
    public ParkingFloor createParkingFloor(ParkingFloor parkingFloor) {
        final String sql = "insert into public.parking_floor(parking_lot_id, floor_number, rows_count) values(?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingFloor.getParkingLot().getLotId());
            ps.setInt(2, parkingFloor.getFloorNumber());
            ps.setInt(3, parkingFloor.getRowsCount());
            return ps;
        }, holder);
        parkingFloor.setFloorId(holder.getKey().intValue());
        return parkingFloor;
    }

    @Override
    public ParkingFloor getById(Integer parking_floor_id) {
        String sql = "select * from public.parking_floor where floorId = ?";
        ParkingFloor parkingFloor = jdbcTemplate.queryForObject(
                sql, new Object[] { parking_floor_id }, parkingFloorRowMapper);
        return parkingFloor;
    }

    @Override
    public List<ParkingFloor> getAvailableFloor(ParkingLot lot) {
        String sql = "select * from public.parking_floor where parking_lot_id = ?";
        List<ParkingFloor> parkingFloors = jdbcTemplate.query(sql, new Object[] { lot.getLotId() }, new BeanPropertyRowMapper(ParkingFloor.class));
        return parkingFloors;
    }

    @Override
    public ParkingSlot parkVehicle(Vehicle vehicle) {
        return null;
    }

    /*@Override
    public boolean markFloorOccupied(Integer parking_floor_id) {
        String sql = "update public.parking_floor set status = 2 where id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, new Object[]{parking_floor_id});
        return rowsUpdated > 1? true: false;
    }*/
}
