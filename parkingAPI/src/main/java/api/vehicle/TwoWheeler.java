package api.vehicle;

public abstract class TwoWheeler implements Vehicle{
    private String name, colour, number;

    public TwoWheeler(String name, String colour, String number) {
        this.name = name;
        this.colour = colour;
        this.number = number;
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
}
