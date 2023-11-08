package de.kleemann.co2_hsharz.core.distance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.Translation;
import com.graphhopper.util.shapes.GHPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.Locale;

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
    private final String OSM_FILE_GERMANY = "C:/Users/Admin/Downloads/germany-latest.osm.pbf";
    @Value("${api.key}")
    private String API_KEY;

    public DistanceCalculationService(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    public GraphHopper createGraphHopperInstance(String ghLoc) {
        GraphHopper graphHopper = new GraphHopper();
        graphHopper.setOSMFile(ghLoc);
        graphHopper.setGraphHopperLocation("target/routing-graph-cache");
        graphHopper.setProfiles(new Profile("car").setVehicle("car").setTurnCosts(false));
        graphHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        graphHopper.importOrLoad();
        return graphHopper;
    }

    /**
     * needs cache files inside target/routing-graph-cache
     * if not exists, first routing takes some time
     */
    public void routing() {
        //https://github.com/graphhopper/graphhopper/blob/master/example/src/main/java/com/graphhopper/example/RoutingExample.java

        GraphHopper hopper = createGraphHopperInstance(OSM_FILE_GERMANY);
        /*
        GHPoint startPoint = new GHPoint(53.54884290760227, 9.98706588622086); // Hamburg
        GHPoint endPoint = new GHPoint(48.13512446680434, 11.581980086843103); // München
         */
        GHPoint startPoint = new GHPoint(51.51360459147637, 7.465319468659699); // Dortmund
        GHPoint endPoint = new GHPoint(51.05044329998134, 13.737287269090805); // Dresden

        GHRequest request = new GHRequest();
        request.addPoint(startPoint);
        request.addPoint(endPoint);
        request.setProfile("car");
        request.setLocale(Locale.GERMANY);

        GHResponse response = hopper.route(request);

        if (response.hasErrors())
            throw new RuntimeException(response.getErrors().toString());

        ResponsePath path = response.getBest();

        PointList pointList = path.getPoints();
        double distance = path.getDistance();
        long timeInMs = path.getTime();

        Translation translation = hopper.getTranslationMap().getWithFallBack(Locale.GERMANY);
        InstructionList instructionList = path.getInstructions();

        System.out.println("\nNavigation:\n");
        for (Instruction instruction : instructionList) {
            System.out.println("distance " + instruction.getDistance() + " for instruction: " + instruction.getTurnDescription(translation));
        }
        System.out.println("\n=====================================\n");
        System.out.println("Total distance in metres: " + distance);
        System.out.println("Total distance in kilometres: " + (distance/1000.0));
        System.out.println("Total time in ms: " + timeInMs);
        System.out.println("Total time in seconds: " + ((double) timeInMs/1000L));
        System.out.println("Total time in minutes: " + (((double)timeInMs/1000L)/60L));
        System.out.println("Total time in hours: " + ((((double) timeInMs/1000L)/60L)/60L));

    }

    public double calculateDistance(String startCity, String endCity) throws IOException {
        GHPoint start = coordinateService.getCoordinatesFromCity(startCity);
        GHPoint end = coordinateService.getCoordinatesFromCity(endCity);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/route?point=" + start + "&point=" + end + "&profile=car&locale=de&calc_points=false&key=" + API_KEY)
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

                    System.out.println("Die ermittelte Distanz zwischen " + startCity + " und " + endCity + " beträgt " + distance + " Meter.");
                    return distance;
                } else {
                    System.out.println("Pfadinformationen nicht gefunden.");
                }
            } else {
                System.out.println("Response-Body ist leer.");
            }
        } else {
            System.out.println("Fehler beim Abrufen der Daten: " + response.code());
        }

        return 0.0;
    }

    public void routingWithAPI2() {
        //get api key
        //GraphHopperWeb
        //https://github.com/graphhopper/graphhopper/blob/master/client-hc/src/test/java/com/graphhopper/api/Examples.java
        /*
        <dependency>
            <groupId>com.graphhopper</groupId>
            <artifactId>graphhopper-web-api</artifactId>
            <version>${project.parent.version}</version>
        </dependency>
         */
        /*
        https://github.com/graphhopper/graphhopper/blob/master/docs/core/profiles.md
        The vehicle field must correspond to one of GraphHopper's built-in vehicle types:
            foot
            bike
            racingbike
            mtb
            car
         */
        //https://docs.graphhopper.com/#operation/getRoute
    }

    /*
        <dependency>
            <groupId>com.graphhopper</groupId>
            <artifactId>graphhopper</artifactId>
            <version>0.7.0</version>
        </dependency>

    public double calculate() {
        String graphHopperLocation = "C:/Users/Admin/Downloads/maps3/germany-latest.osm.pbf";
        //String graphHopperLocation = "C:/Users/Admin/Downloads/maps2/sachsen-anhalt-latest.osm.pbf";

        // Erstellen und initialisieren Sie den GraphHopper-Router
        GraphHopper graphHopper2 = new GraphHopper().forServer();
        graphHopper.setOSMFile(graphHopperLocation);
        graphHopper.setGraphHopperLocation("C:/Users/Admin/Downloads/maps3");
        graphHopper.setEncodingManager(new EncodingManager("bike"));

        // Laden der Datenbank
        graphHopper.importOrLoad();

        // Start- und Zielpunkt
        GHPoint startPoint = new GHPoint(52.120533, 11.627624); // Magdeburg
        //GHPoint startPoint = new GHPoint(51.834081, 10.782776); // Wernigerode
        //GHPoint startPoint = new GHPoint(52.96938731256507, 11.623643350319496); // Politz
        //GHPoint startPoint = new GHPoint(53.54884290760227, 9.98706588622086); // Hamburg
        //GHPoint endPoint = new GHPoint(51.834081, 10.782776); // Wernigerode
        //GHPoint endPoint = new GHPoint(51.49033173903856, 11.966218714650264); // Halle
        GHPoint endPoint = new GHPoint(48.13512446680434, 11.581980086843103); // München

        GHRequest request = new GHRequest();
        request.addPoint(startPoint);
        request.addPoint(endPoint);

        // Berechnen Sie die Route
        GHResponse response = graphHopper.route(request);

        // Ausgabe der Entfernung der Route
        if (response.hasErrors()) {
            System.out.println("Fehler bei der Routenberechnung: " + response.getErrors());
        } else {
            System.out.println("Entfernung der Route (in Metern): " + response.getBest().getDistance());
            response.getBest().getDescription().stream().forEach(s -> System.err.println(s));
            System.err.println("Dauer: " + response.getBest().getTime());
            System.err.println("Weight: " + response.getBest().getRouteWeight());
            graphHopper.close();

            return response.getBest().getDistance();
        }
        graphHopper.close();
        return -1;
    }

     */

}
