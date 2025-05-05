package oracle.spatial.oracleDatabase.dtos.Polygon;

public record RequestPolygonDTO(String name,
                                double[] longitude,
                                double[] latitude) {
}
