package domain;

import com.sapient.assignment.parkinglot.domain.vehicle.Car;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.domain.vehicle.VehicleType;
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
        assertTrue(this.car.getVehicleType().equals(VehicleType.CAR));
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
        assertEquals(this.car.getRegNumber(), "BUGATTI-91");
    }
}