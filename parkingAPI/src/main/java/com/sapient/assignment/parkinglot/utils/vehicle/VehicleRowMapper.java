package com.sapient.assignment.parkinglot.utils.vehicle;

import com.sapient.assignment.parkinglot.domain.vehicle.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class VehicleRowMapper implements RowMapper<Vehicle> {

    @Nullable
    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        VehicleType vehicleType = VehicleType.getByVal(rs.getInt("vehicle_type"));
        switch(vehicleType.name().toUpperCase()) {
            case "BIKE":
                Vehicle bike = new Bike(rs.getString("name"), rs.getString("colour"), rs.getString("reg_num"));
                bike.setVehicleId(rs.getInt("vehicleId"));
                return bike;

            case "CAR":
                Vehicle car = new Car(rs.getString("name"), rs.getString("colour"), rs.getString("reg_num"));
                car.setVehicleId(rs.getInt("vehicleId"));
                return car;

            case "BUS":
                Vehicle bus = new Bus(rs.getString("name"), rs.getString("colour"), rs.getString("reg_num"));
                bus.setVehicleId(rs.getInt("vehicleId"));
                return bus;
        }
        throw new IllegalArgumentException();
    }
}
