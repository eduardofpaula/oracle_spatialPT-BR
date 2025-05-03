package oracle.spatial.oracleDatabase.services.utils;

import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GeometryUtils {

    public static Point createPoint(double latitude, double longitude){
        if(latitude == 0 || longitude == 0){
            throw new IllegalArgumentException("Latitude ou Longitude vazios");
        }

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate pointCoordinates = new Coordinate(longitude,latitude);
        return geometryFactory.createPoint(pointCoordinates);
    }
}
