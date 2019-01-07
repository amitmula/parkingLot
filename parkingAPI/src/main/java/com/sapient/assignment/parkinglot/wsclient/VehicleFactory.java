package com.sapient.assignment.parkinglot.wsclient;

import com.sapient.assignment.parkinglot.domain.vehicle.Bike;
import com.sapient.assignment.parkinglot.domain.vehicle.Bus;
import com.sapient.assignment.parkinglot.domain.vehicle.Car;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;

public class VehicleFactory {

    public static Vehicle getVehicle(com.sapient.assignment.parkinglot.wsclient.wsdl.Vehicle vehicle){
        switch(vehicle.getType().toUpperCase()) {
            case "BIKE":
            return new Bike(vehicle.getName(), vehicle.getColour(), vehicle.getNumber());

            case "CAR":
            return new Car(vehicle.getName(), vehicle.getColour(), vehicle.getNumber());

            case "BUS":
            return new Bus(vehicle.getName(), vehicle.getColour(), vehicle.getNumber());
        }
        throw new IllegalArgumentException(vehicle.getType() + " isn't a valid vehicle type value.");
    }
}
