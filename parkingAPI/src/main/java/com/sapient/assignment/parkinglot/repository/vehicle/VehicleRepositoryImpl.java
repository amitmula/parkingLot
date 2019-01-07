package com.sapient.assignment.parkinglot.repository.vehicle;

import com.sapient.assignment.parkinglot.domain.vehicle.*;
import com.sapient.assignment.parkinglot.utils.vehicle.VehicleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VehicleRepositoryImpl implements VehicleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    VehicleRowMapper vehicleRowMapper;

    @Override
    public Vehicle createVehicle(final Vehicle vehicle) {
        final String sql = "insert into public.vehicle(name,colour,reg_num,vehicle_type) values(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehicle.getName());
            ps.setString(2, vehicle.getColour());
            ps.setString(3, vehicle.getRegNumber());
            ps.setInt(4, vehicle.getVehicleType().getVal());
            return ps;
        }, holder);
        vehicle.setVehicleId(holder.getKey().intValue());
        return vehicle;
    }

    @Override
    public Vehicle getById(Integer vehicle_id) {
        String sql = "select * from public.vehicle where vehicleId = ?";
        Vehicle vehicle = (Vehicle)jdbcTemplate.queryForObject(
                sql, new Object[] { vehicle_id }, vehicleRowMapper);
        return vehicle;
    }

    @Override
    public Boolean isVehicleExists(String reg_num) {
        String sql = "select count(*) from public.vehicle where reg_num = ?";
        boolean result = false;
        int count = jdbcTemplate.queryForObject(sql, new Object[] { reg_num }, Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public List<Vehicle> getAll() {
        String sql = "select * from public.vehicle";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Vehicle> vehicles = new ArrayList();
        for (Map row : rows) {
            Integer vehicle_type = (Integer) row.get("vehicle_type");
            Vehicle vehicle = null;
            switch(VehicleType.getByVal(vehicle_type)) {
                case BIKE:
                    vehicle = new Bike(row.get("name").toString(), row.get("colour").toString(), row.get("reg_num").toString());
                    break;
                case CAR:
                    vehicle = new Car(row.get("name").toString(), row.get("colour").toString(), row.get("reg_num").toString());
                    break;
                case BUS:
                    vehicle = new Bus(row.get("name").toString(), row.get("colour").toString(), row.get("reg_num").toString());
                    break;
            }
            vehicle.setVehicleId((int)row.get("vehicleId"));
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    @Override
    public Vehicle getVehicleByRegNum(String reg_number) {
        String sql = "select * from public.vehicle where reg_num = ?";
        Vehicle vehicle = (Vehicle)jdbcTemplate.queryForObject(
            sql, new Object[] { reg_number }, vehicleRowMapper);
        return vehicle;
    }
}
