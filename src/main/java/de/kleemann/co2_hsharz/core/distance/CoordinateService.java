package de.kleemann.co2_hsharz.core.distance;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.util.shapes.GHPoint;

import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * This Service provides core layer functionality to retrieve the coordinate of a city by using the graphhopper api
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 08.11.2023
 */
@Service
public class CoordinateService {

	/**
	 * API Key for the Graphhopper API <br>
	 * Defined in the application.properties
	 */
    @Value("${api.key}")
    private String API_KEY;

    /**
     * Default Constructor
     */
    public CoordinateService() {

    }

    /**
     * Retrieves a GraphHopper Point containing the coordinates from a city
     * @param city - {@link String} Name of the City
     * @return {@link GHPoint} containing the coordinates
     * @throws IOException if GraphHopper API is not available or Json Object Mapping failed
     */
    public GHPoint getCoordinatesFromCity(@NonNull String city) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/geocode?q=" + city + "&locale=de&key=" + API_KEY)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(json);

                JsonNode hits = root.get("hits");
                JsonNode firstHit = hits.get(0);
                JsonNode coordinates = firstHit.get("point");
                if (coordinates != null) {
                    double latitude = coordinates.get("lat").asDouble();
                    double longitude = coordinates.get("lng").asDouble();

                    GHPoint resultPoint = new GHPoint(latitude, longitude);

                    System.out.println("Koordinaten für " + city + ":");
                    System.out.println("Breitengrad: " + latitude);
                    System.out.println("Längengrad: " + longitude);
                    return resultPoint;
                } else {
                    System.out.println("Koordinaten nicht gefunden.");
                }
            } else {
                System.out.println("Response-Body ist leer.");
            }
        } else {
            System.out.println("Fehler beim Abrufen der Daten: " + response.code());
        }
        return null;
    }

}
