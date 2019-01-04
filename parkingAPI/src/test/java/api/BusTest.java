package api;

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
    public void assertInstanceType() throws Exception {
        assertTrue(this.bus instanceof Bus);
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
        assertEquals(this.bus.getNumber(), "SUPER-91");
    }

}