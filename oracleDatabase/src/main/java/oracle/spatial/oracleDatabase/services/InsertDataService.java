package oracle.spatial.oracleDatabase.services;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import oracle.spatial.oracleDatabase.dtos.LineString.RequestLineStringDTO;
import oracle.spatial.oracleDatabase.dtos.Point.RequestPointDTO;
import oracle.spatial.oracleDatabase.dtos.Polygon.RequestPolygonDTO;
import oracle.spatial.oracleDatabase.entities.LocationSpatial;
import oracle.spatial.oracleDatabase.repositories.LocationRepository;
import oracle.spatial.oracleDatabase.services.utils.GeometryUtils;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            locationRepository.save(point);
        }
    }

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

            LocationSpatial lineString = new LocationSpatial();
            LineString geometryLineString = GeometryUtils.createLineString(dto.longitude(), dto.latitude());

            lineString.setName(dto.name());
            lineString.setCoordinate(geometryLineString);

            locationRepository.save(lineString);
        }
    }

    @Operation(summary = "Inserir polígonos geométricos", description = "Método para inserir id, nome e poligono no banco de dados")
    @Transactional
    public void inserirPolygon(List<RequestPolygonDTO> requestPolygonDTO) {
        if (requestPolygonDTO == null || requestPolygonDTO.isEmpty()) {
            throw new IllegalArgumentException("Lista de polígonos vazia");
        }

        for (RequestPolygonDTO dto : requestPolygonDTO) {

            if (dto.longitude() == null || dto.latitude() == null ||
                    dto.longitude().length != dto.latitude().length || dto.longitude().length < 3) {
                throw new IllegalArgumentException("Dados inválidos no polígono: " + dto.name());
            }

            LocationSpatial polygon = new LocationSpatial();
            Polygon geometryPolygon = GeometryUtils.createPolygon(dto.longitude(), dto.latitude());

            polygon.setName(dto.name());
            polygon.setCoordinate(geometryPolygon);
            locationRepository.save(polygon);
        }
    }


}
