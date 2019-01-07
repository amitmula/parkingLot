package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.parking.SlotSize;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.utils.parking.ParkingSlotRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class ParkingSlotRepoImpl implements ParkingSlotRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ParkingSlotRowMapper parkingSlotRowMapper;

    @Override
    public ParkingSlot createParkingSlot(ParkingSlot parkingSlot) {
        final String sql = "insert into public.parking_slot(row_id, slot_number, slot_size, vehicle_id, status) values(?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingSlot.getParkingRow().getRowId());
            ps.setInt(2, parkingSlot.getSlotNumber());
            ps.setInt(3, parkingSlot.getSlotSize().getVal());
            ps.setInt(4, parkingSlot.getVehicle().getVehicleId());
            ps.setInt(5, parkingSlot.getStatus().getVal());
            return ps;
        }, holder);
        parkingSlot.setSlotId(holder.getKey().intValue());
        return parkingSlot;
    }

    @Override
    public ParkingSlot getById(Integer parking_slot_id) {
        String sql = "select * from public.parking_slot where slotId = ?";
        ParkingSlot parkingSlot = (ParkingSlot)jdbcTemplate.queryForObject(
                sql, new Object[] { parking_slot_id }, new ParkingSlotRowMapper());
        return parkingSlot;
    }

    @Override
    public List<ParkingSlot> findAll() {
        return jdbcTemplate.query("select * from parking_slot", parkingSlotRowMapper);
    }

    @Override
    public List<ParkingSlot> getAllSlots(ParkingRow row) {
        String sql = "select * from public.parking_slot where row_id = ?";
        List<ParkingSlot> parkingSlots = jdbcTemplate.query(sql, new Object[] { row.getRowId() }, parkingSlotRowMapper);
        return parkingSlots;
    }

    @Override
    public Integer getRowSlotsCountBySize(SlotSize slotSize, ParkingRow row) {
        String sql = "select count(*) from public.parking_slot where row_id = ? and slot_size = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { row.getRowId(), slotSize.getVal() }, Integer.class);
    }

    @Override
    public ParkingSlot findVehicle(Vehicle vehicle) {
        String sql = "select * from public.parking_slot where vehicle_id = ?";
        ParkingSlot parkingSlot = (ParkingSlot)jdbcTemplate.queryForObject(
                sql, new Object[] { vehicle.getVehicleId() }, parkingSlotRowMapper);
        return parkingSlot;
    }

    @Override
    public ParkingSlot park(Vehicle vehicle, ParkingRow row) {
        return null;
    }
}
