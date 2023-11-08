package de.kleemann.co2_hsharz.core.distance;

import com.graphhopper.util.shapes.GHPoint;

/**
 * Class "Distance" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 08.11.2023
 */
public class Distance {

    private GHPoint startPoint;
    private GHPoint endPoint;

    public Distance() {

    }

    public Distance(GHPoint startPoint, GHPoint endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public GHPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(GHPoint startPoint) {
        this.startPoint = startPoint;
    }

    public GHPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(GHPoint endPoint) {
        this.endPoint = endPoint;
    }
}
