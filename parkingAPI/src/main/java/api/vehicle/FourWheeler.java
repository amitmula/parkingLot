package api.vehicle;

public abstract class FourWheeler implements Vehicle {
    private String name, colour, number;
    private VehicleType type;

    public FourWheeler(String name, String colour, String number, VehicleType type) {
        this.name = name;
        this.colour = colour;
        this.number = number;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public VehicleType getType() {
        return type;
    }
}
