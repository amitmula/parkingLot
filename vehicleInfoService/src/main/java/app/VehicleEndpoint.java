package app;

import assignment.vehicleinfo.GetVehicleRequest;
import assignment.vehicleinfo.GetVehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class VehicleEndpoint {
	private static final String NAMESPACE_URI = "http://assignment/vehicleinfo";

	private VehicleRepository vehicleRepository;

	@Autowired
	public VehicleEndpoint(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVehicleRequest")
	@ResponsePayload
	public GetVehicleResponse getVehicle(@RequestPayload GetVehicleRequest request) {
		GetVehicleResponse response = new GetVehicleResponse();
		response.setVehicle(vehicleRepository.findVehicle(request.getNumber()));
		return response;
	}
}
