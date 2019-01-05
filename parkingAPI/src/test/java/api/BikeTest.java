package api;

import api.vehicle.Bike;
import api.vehicle.Vehicle;
import api.vehicle.VehicleType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BikeTest {
    private Vehicle bike;
    @Before
    public void setUp() throws Exception {
        bike = new Bike("Dodge", "white", "DODGE-91");
    }

    @Test
    public void assertVehicleType() throws Exception {
        assertTrue(this.bike.getType().equals(VehicleType.BIKE));
    }

    @Test
    public void getColour() throws Exception {
        assertEquals(this.bike.getColour(), "white");
    }

    @Test
    public void getName() throws Exception {
        assertEquals(this.bike.getName(), "Dodge");
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(this.bike.getNumber(), "DODGE-91");
    }

}