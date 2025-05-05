package oracle.spatial.oracleDatabase.services.utils;

import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;

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

    public static LineString createLineString(double[] longitude, double[] latitude){
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException("Longitude e latitude não podem ser nulos");
        }

        if (longitude.length != latitude.length || longitude.length < 2) {
            throw new IllegalArgumentException("Longitude e latitude devem ter o mesmo tamanho e pelo menos 2 pontos");
        }

        Coordinate[] coordinates = new Coordinate[longitude.length];
        for (int i = 0; i < longitude.length; i++) {
            coordinates[i] = new Coordinate(longitude[i], latitude[i]);
        }

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createLineString(coordinates);

    }

    public static Polygon createPolygon(double[] longitude, double[] latitude) {
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException("Longitude e latitude não podem ser nulos");
        }

        if (longitude.length != latitude.length) {
            throw new IllegalArgumentException("Longitude e latitude devem ter o mesmo tamanho");
        }

        if (longitude.length < 3) {
            throw new IllegalArgumentException("É necessário pelo menos 3 pontos para formar um polígono");
        }

        int n = longitude.length;
        Coordinate[] coordinates = new Coordinate[n + 1];

        for (int i = 0; i < n; i++) {
            if (Double.isNaN(longitude[i]) || Double.isNaN(latitude[i])) {
                throw new IllegalArgumentException("Coordenadas não podem ser NaN");
            }

            coordinates[i] = new Coordinate(longitude[i], latitude[i]);
        }

        coordinates[n] = new Coordinate(longitude[0], latitude[0]);

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        LinearRing shell = geometryFactory.createLinearRing(coordinates);
        return geometryFactory.createPolygon(shell, null);
    }


}
