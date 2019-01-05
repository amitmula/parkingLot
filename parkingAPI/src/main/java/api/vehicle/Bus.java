package api.vehicle;

public class Bus extends FourWheeler {

    public Bus(String name, String colour, String number) {
        super(name, colour, number, VehicleType.BUS);
    }
}
