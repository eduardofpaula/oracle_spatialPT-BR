package oracle.spatial.oracleDatabase.services.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

@Component
public class GeometryUtils {

    public static Point createPoint(double latitude, double longitude) {
        if (latitude == 0 || longitude == 0) {
            throw new IllegalArgumentException("Latitude ou Longitude vazios");
        }

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate pointCoordinates = new Coordinate(longitude, latitude);
        return geometryFactory.createPoint(pointCoordinates);
    }

}
