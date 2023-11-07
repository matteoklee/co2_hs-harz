package de.kleemann.co2_hsharz.core.distance;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.HintsMap;
import com.graphhopper.util.shapes.GHPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Class "DistanceCalculationService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.11.2023
 */
@Service
public class DistanceCalculationService {


    public DistanceCalculationService() {

    }

    public double calculate() {
        //String graphHopperLocation = "C:/Users/Admin/Downloads/maps/germany-latest.osm.pbf";
        String graphHopperLocation = "C:/Users/Admin/Downloads/maps2/sachsen-anhalt-latest.osm.pbf";

        // Erstellen und initialisieren Sie den GraphHopper-Router
        GraphHopper graphHopper = new GraphHopper().forServer();
        graphHopper.setOSMFile(graphHopperLocation);
        graphHopper.setGraphHopperLocation("C:/Users/Admin/Downloads/maps2");
        graphHopper.setEncodingManager(new EncodingManager("car"));

        // Laden der Datenbank
        graphHopper.importOrLoad();

        // Start- und Zielpunkt
        //GHPoint startPoint = new GHPoint(52.120533, 11.627624); // Magdeburg
        //GHPoint startPoint = new GHPoint(51.834081, 10.782776); // Wernigerode
        GHPoint startPoint = new GHPoint(52.96938731256507, 11.623643350319496); // Politz
        //GHPoint endPoint = new GHPoint(51.834081, 10.782776); // Wernigerode
        GHPoint endPoint = new GHPoint(51.49033173903856, 11.966218714650264); // Halle

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

}
