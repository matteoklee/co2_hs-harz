package de.kleemann.co2_hsharz.core.distance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.util.shapes.GHPoint;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class "DistanceCalculationService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.11.2023
 */
@Service
public class DistanceCalculationService {

    @Value("${api.key}")
    private String API_KEY;

    @Value("${api.key.google}")
    private String API_KEY_GOOGLE;

    public DistanceCalculationService() {

    }

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
            System.err.println("reponse was not successful. " + response.code());
            return 0;
        }
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            System.err.println("response body is null.");
            return 0;
        }
        String  json = responseBody.string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(json);

        JsonNode distanceNode = root.findValue("distance");
        if (distanceNode == null || !distanceNode.isObject()) {
           System.err.println("distanceNode cannot be read.");
           return 0;
        }
        double distance = distanceNode.findValue("value").asDouble();
        System.out.println("Distance: " + distance);
        System.out.println("durationNode: " + root.findValue("duration"));
        return distance;
    }

}
