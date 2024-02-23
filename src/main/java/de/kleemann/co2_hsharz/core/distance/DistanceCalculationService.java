package de.kleemann.co2_hsharz.core.distance;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import com.graphhopper.util.shapes.GHPoint;

import lombok.NonNull;
=======
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
>>>>>>> refs/heads/dev
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * This Service provides core layer functionality to calculate the distance between two cities by using the graphhopper api
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.11.2023
 */
@Service
public class DistanceCalculationService {

<<<<<<< HEAD
	/**
	 * {@link CoordinateService}
	 */
    private final CoordinateService coordinateService;
    
    /**
	 * API Key for the Graphhopper API <br>
	 * Defined in the application.properties
	 */
=======
>>>>>>> refs/heads/dev
    @Value("${api.key}")
    private String API_KEY;

<<<<<<< HEAD
    /**
     * Constructs a {@link DistanceCalculationService} using the {@link CoordinateService}
     * @param coordinateService - {@link CoordinateService}
     */
    public DistanceCalculationService(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
=======
    @Value("${api.key.google}")
    private String API_KEY_GOOGLE;

    public DistanceCalculationService() {

>>>>>>> refs/heads/dev
    }

<<<<<<< HEAD
    /**
     * Calculates the distance between two cities using the Graphhopper API
     * @param startLocation - {@link String} Name of the starting city
     * @param endLocation - {@link String} Name of the ending city
     * @return {@code double} Distance between these two cities in meters
     * @throws IOException
     */
    public double calculateDistance(@NonNull String startLocation, @NonNull String endLocation) throws IOException {
        GHPoint start = coordinateService.getCoordinatesFromCity(startLocation);
        GHPoint end = coordinateService.getCoordinatesFromCity(endLocation);

        //Eingabe auf country == Deutschland testen
=======
    public double calculateDistance(String startLocation, String endLocation, TransportMediumDTO transportMediumDTO) throws IOException {
        /*
        https://developers.google.com/maps/documentation/distance-matrix/distance-matrix?hl=de#mode
        mode:
         - driving
         - walking
         - bicycling
         - transit
        transit_mode:
         - bus
         - subway
         - train
         - tram
         - rail --> transit_mode=train|tram|subway
         */
        String transportMediumNameString = transportMediumDTO.getTransportMediumName();
        TransportMediumName transportMediumName = TransportMediumName.fromName(transportMediumNameString);
>>>>>>> refs/heads/dev

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?destinations="
                        + endLocation + "&origins=" + startLocation + "&mode=" + transportMediumName.getTransportMediumMode()
                        + "&key=" + API_KEY_GOOGLE)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        return getDistanceFromMaps(response);
    }


    private double getDistanceFromMaps(Response response) throws IOException {
        if (!response.isSuccessful()) {
            //TODO: throw error
            System.err.println("reponse was not successful. " + response.code());
            return 0;
        }
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            //TODO: throw error
            System.err.println("response body is null.");
            return 0;
        }
        String  json = responseBody.string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(json);

        JsonNode distanceNode = root.findValue("distance");
        if (distanceNode == null || !distanceNode.isObject()) {
           System.err.println("distanceNode cannot be read.");
           //TODO: throw error
           return 0;
        }
        double distance = distanceNode.findValue("value").asDouble();
        //System.out.println("Distance: " + distance);
        //System.out.println("durationNode: " + root.findValue("duration"));
        return distance;
    }

}
