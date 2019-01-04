package api;

public class Bus extends FourWheeler {

    public Bus(String name, String colour, String number) {
        super(name, colour, number);
    }

    @Override
    public String getColour() {
        return super.getColour();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getNumber() {
        return super.getNumber();
    }
}
