package com.sapient.assignment.parkinglot.service.vehicle;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.wsclient.VehicleFactory;
import com.sapient.assignment.parkinglot.repository.vehicle.VehicleRepository;
import com.sapient.assignment.parkinglot.wsclient.VehicleInfoServiceClient;
import com.sapient.assignment.parkinglot.wsclient.wsdl.GetVehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleInfoServiceClient vehicleInfoServiceClient;

    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle getVehicleById(Integer id) {
        return vehicleRepository.getById(id);
    }

    public Vehicle createVehicle(final Vehicle vehicle) {
        return vehicleRepository.createVehicle(vehicle);
    }

    public Vehicle getVehicleInfo(String regNumber) {
        GetVehicleResponse vehicleInfo = vehicleInfoServiceClient.getVehicleInfo(regNumber);
        if(vehicleInfo.getVehicle() == null) throw new IllegalArgumentException("Vehicle with reg number : " + regNumber + " is not found");
        com.sapient.assignment.parkinglot.wsclient.wsdl.Vehicle vehicle = vehicleInfo.getVehicle();
        return VehicleFactory.getVehicle(vehicle);
    }

    public ParkingSlot parkVehicle(String vehicle_number) {
        GetVehicleResponse vehicleInfo = vehicleInfoServiceClient.getVehicleInfo(vehicle_number);
        com.sapient.assignment.parkinglot.wsclient.wsdl.Vehicle vehicle = vehicleInfo.getVehicle();
        return null;
    }

    public Vehicle saveVehicleInfo(String vehicle_number) {
        Vehicle vehicleInfo = getVehicleInfo(vehicle_number);
        return createVehicle(vehicleInfo);
    }

    public Boolean isVehicleExists(String reg_num) {
        return vehicleRepository.isVehicleExists(reg_num);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.getAll();

    }

    public Vehicle getVehicleByRegNum(String reg_number) {
        return vehicleRepository.getVehicleByRegNum(reg_number);
    }
}
