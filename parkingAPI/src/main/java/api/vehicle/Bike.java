package api.vehicle;

public class Bike extends TwoWheeler {

    public Bike(String name, String colour, String number) {
        super(name, colour, number, VehicleType.BIKE);
    }
}
