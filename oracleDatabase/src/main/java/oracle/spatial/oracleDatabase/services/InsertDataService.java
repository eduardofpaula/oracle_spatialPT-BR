package oracle.spatial.oracleDatabase.services;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import oracle.spatial.oracleDatabase.dtos.LineString.RequestLineStringDTO;
import oracle.spatial.oracleDatabase.dtos.Point.RequestPointDTO;
import oracle.spatial.oracleDatabase.entities.LocationSpatial;
import oracle.spatial.oracleDatabase.repositories.LocationRepository;
import oracle.spatial.oracleDatabase.services.utils.GeometryUtils;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class InsertDataService {

    @Autowired
    LocationRepository locationRepository;

    @Operation(summary = "Inserir ponto geométrico", description = "Método que insere id, nome e ponto geométrico no banco de dados")
    @Transactional
    public void insertPoint(List<RequestPointDTO> requestPointDTO) {

        if (requestPointDTO.isEmpty()) {
            throw new IllegalArgumentException("Nome do ponto geométrico vazio");
        }


        for (RequestPointDTO requestPoint : requestPointDTO) {

            LocationSpatial point = new LocationSpatial();
            Point geometryPoint = GeometryUtils.createPoint(requestPoint.longitude(), requestPoint.latitude());

            point.setName(requestPoint.name());
            point.setCoordinate(geometryPoint);

            locationRepository.insertPointDatabase(requestPoint.name(), requestPoint.longitude(), requestPoint.latitude());
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Operation(summary = "Inserir linha(rota) geométrica", description = "Método para inserir id, nome e rota(pode conter N pontos) no banco de dados")
    @Transactional
    public void inserirLineString(List<RequestLineStringDTO> requestLineStringDTO) {
        if (requestLineStringDTO.isEmpty()) {
            throw new IllegalArgumentException("Rota vazia");
        }

        for (RequestLineStringDTO dto : requestLineStringDTO) {
            if (dto.latitude().length == 0 || dto.longitude().length == 0) {
                throw new IllegalArgumentException("Latitude ou Longitude vazios");
            }

            if (dto.longitude().length != dto.latitude().length) {
                throw new IllegalArgumentException("Latitude e Longitude com tamanhos diferentes");
            }

            Double[] ordinateArray = buildOrdinateArray(dto.longitude(), dto.latitude());
            buildTemplate(dto.name(), ordinateArray);
        }
    }

    public void buildTemplate(String name, Double[] coordinates) {
        StringBuilder ordinateSql = new StringBuilder();
        for (int i = 0; i < coordinates.length; i++) {
            ordinateSql.append(round(coordinates[i]));
            if (i < coordinates.length - 1) {
                ordinateSql.append(", ");
            }
        }

        String sql = String.format("""
                INSERT INTO locationspatial (NAME, COORDINATE)
                            VALUES (
                                ?,\s
                                SDO_GEOMETRY(
                                    2002, 4326, NULL,\s
                                    SDO_ELEM_INFO_ARRAY(1,2,1),\s
                                    SDO_ORDINATE_ARRAY(%s)
                                )
                            )
                """, ordinateSql);

        jdbcTemplate.update(sql, name);
    }

    public static Double[] buildOrdinateArray(double[] longitude, double[] latitude) {
        Double[] ordinates = new Double[longitude.length * 2];
        for (int i = 0, j = 0; i < longitude.length; i++) {
            ordinates[j++] = round(longitude[i]);
            ordinates[j++] = round(latitude[i]);
        }
        return ordinates;
    }

    private static double round(double value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
