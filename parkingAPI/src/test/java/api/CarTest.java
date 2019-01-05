package api;

import api.vehicle.Car;
import api.vehicle.Vehicle;
import api.vehicle.VehicleType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    private Vehicle car;
    @Before
    public void setUp() throws Exception {
        car = new Car("Bugatti", "white", "BUGATTI-91");
    }

    @Test
    public void assertVehicleType() throws Exception {
        assertTrue(this.car.getType().equals(VehicleType.CAR));
    }

    @Test
    public void getColour() throws Exception {
        assertEquals(this.car.getColour(), "white");
    }

    @Test
    public void getName() throws Exception {
        assertEquals(this.car.getName(), "Bugatti");
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(this.car.getNumber(), "BUGATTI-91");
    }
}