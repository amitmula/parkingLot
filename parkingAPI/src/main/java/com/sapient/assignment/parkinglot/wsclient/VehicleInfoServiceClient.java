package com.sapient.assignment.parkinglot.wsclient;

import com.sapient.assignment.parkinglot.wsclient.wsdl.GetVehicleRequest;
import com.sapient.assignment.parkinglot.wsclient.wsdl.GetVehicleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class VehicleInfoServiceClient extends WebServiceGatewaySupport {


    @Value("${vehicleInfoServiceWDSL}")
    private String vehicleInfoServiceWDSL;

    public GetVehicleResponse getVehicleInfo(String reg_number) {

        GetVehicleRequest request = new GetVehicleRequest();
        request.setNumber(reg_number);
        GetVehicleResponse response = (GetVehicleResponse) getWebServiceTemplate()
        .marshalSendAndReceive(vehicleInfoServiceWDSL, request,
            new SoapActionCallback("http://assignment/vehicleinfo")
        );
        return response;
    }
}
