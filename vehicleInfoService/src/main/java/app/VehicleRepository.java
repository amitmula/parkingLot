package app;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import assignment.vehicleinfo.Vehicle;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class VehicleRepository {
	private static final Map<String, Vehicle> vehicles = new HashMap<>();

	@PostConstruct
	public void initData() {
		Vehicle bugatti = new Vehicle();
		bugatti.setName("Bugatti Veron");
		bugatti.setColour("White");
		bugatti.setNumber("BUGATTI-01");
		bugatti.setType("Car");

		vehicles.put(bugatti.getNumber(), bugatti);

		Vehicle dodge = new Vehicle();
		dodge.setName("Dodge Tomahawk");
		dodge.setColour("White");
		dodge.setNumber("TOMAHAWK-01");
		dodge.setType("Bike");


		vehicles.put(dodge.getNumber(), dodge);

		Vehicle superBus = new Vehicle();
		superBus.setName("Super Bus");
		superBus.setColour("White");
		superBus.setNumber("SUPER-01");
		superBus.setType("Bus");

		vehicles.put(superBus.getNumber(), superBus);
	}

	public Vehicle findVehicle(String number) {
		Assert.notNull(number, "The vehicle's number must not be null");
		return vehicles.get(number);
	}
}
