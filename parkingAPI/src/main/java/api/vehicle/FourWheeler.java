package api.vehicle;

public abstract class FourWheeler implements Vehicle {
    private String name, colour, number;

    public FourWheeler(String name, String colour, String number) {
        this.name = name;
        this.colour = colour;
        this.number = number;
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
}
