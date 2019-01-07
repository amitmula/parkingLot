package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.utils.parking.ParkingLotRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ParkingLotRepoImpl implements ParkingLotRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ParkingLotRowMapper parkingLotRowMapper;

    @Override
    public ParkingLot createParkingLot(ParkingLot parkingLot) {
        final String sql = "insert into public.parking_lot(floor_count) values(?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingLot.getFloorCount());
            return ps;
        }, holder);
        parkingLot.setLotId(holder.getKey().intValue());
        return parkingLot;
    }

    @Override
    public ParkingLot getById(Integer parking_lot_id) {
        String sql = "select * from public.parking_lot where lotId = ?";
        ParkingLot parkingLot = (ParkingLot)jdbcTemplate.queryForObject(
                sql, new Object[] { parking_lot_id }, parkingLotRowMapper);
        return parkingLot;
    }

    @Override
    public List<ParkingLot> getAll() {
        String sql = "select * from public.parking_lot";
        List<ParkingLot> parkingLots = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ParkingLot.class));
        return parkingLots;
    }
}
