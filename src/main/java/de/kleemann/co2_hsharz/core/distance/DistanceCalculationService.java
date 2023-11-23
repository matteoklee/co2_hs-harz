package de.kleemann.co2_hsharz.core.distance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.util.shapes.GHPoint;
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

    private final CoordinateService coordinateService;
    @Value("${api.key}")
    private String API_KEY;

    public DistanceCalculationService(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    public double calculateDistance(String startLocation, String endLocation) throws IOException {
        GHPoint start = coordinateService.getCoordinatesFromCity(startLocation);
        GHPoint end = coordinateService.getCoordinatesFromCity(endLocation);

        //Eingabe auf country == Deutschland testen

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/route?point=" + start + "&point=" + end + "&profile=bike&locale=de&calc_points=false&key=" + API_KEY)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(json);

                JsonNode paths = root.get("paths");
                if (paths != null && paths.isArray() && paths.size() > 0) {
                    JsonNode firstPath = paths.get(0);
                    double distance = firstPath.get("distance").asDouble();

                    System.out.println("Die ermittelte Distanz zwischen " + startLocation + " und " + endLocation + " beträgt " + distance + " Meter.");
                    return distance;
                } else {
                    System.out.println("Pfadinformationen nicht gefunden.");
                }
            } else {
                System.out.println("Response-Body ist leer.");
            }
        } else {
            System.out.println("Fehler beim Abrufen der Daten: " + response.code());
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();
                System.err.println(json);
            }
        }

        return 0.0;
    }


}
