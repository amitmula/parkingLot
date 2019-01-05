package api.vehicle;

public abstract class TwoWheeler implements Vehicle{
    private String name, colour, number;
    private VehicleType type;

    public TwoWheeler(String name, String colour, String number, VehicleType type) {
        this.name = name;
        this.colour = colour;
        this.number = number;
        this.type = type;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public VehicleType getType() {
        return type;
    }
}
