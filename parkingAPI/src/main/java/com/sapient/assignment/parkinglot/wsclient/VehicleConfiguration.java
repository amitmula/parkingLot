package com.sapient.assignment.parkinglot.wsclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class VehicleConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.sapient.assignment.parkinglot.wsclient.wsdl");
        return marshaller;
    }

    @Bean
    public VehicleInfoServiceClient vehicleInfoServiceClient(Jaxb2Marshaller marshaller) {
        VehicleInfoServiceClient client = new VehicleInfoServiceClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
