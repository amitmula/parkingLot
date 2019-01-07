package domain;

import com.sapient.assignment.parkinglot.domain.vehicle.Bus;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.domain.vehicle.VehicleType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BusTest {
    private Vehicle bus;
    @Before
    public void setUp() throws Exception {
        bus = new Bus("Super Bus", "white", "SUPER-91");
    }

    @Test
    public void assertVehicleType() throws Exception {
        assertTrue(this.bus.getVehicleType().equals(VehicleType.BUS));
    }

    @Test
    public void getColour() throws Exception {
        assertEquals(this.bus.getColour(), "white");
    }

    @Test
    public void getName() throws Exception {
        assertEquals(this.bus.getName(), "Super Bus");
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(this.bus.getRegNumber(), "SUPER-91");
    }

}