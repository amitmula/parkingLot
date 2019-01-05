package api.vehicle;

public class Car extends FourWheeler {

    public Car(String name, String colour, String number) {
        super(name, colour, number, VehicleType.CAR);
    }
}
