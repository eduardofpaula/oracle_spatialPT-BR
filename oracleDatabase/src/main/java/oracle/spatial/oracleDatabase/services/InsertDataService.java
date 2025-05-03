package oracle.spatial.oracleDatabase.services;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import oracle.spatial.oracleDatabase.dtos.Point.RequestPointDTO;
import oracle.spatial.oracleDatabase.entities.LocationSpatial;
import oracle.spatial.oracleDatabase.repositories.LocationRepository;
import oracle.spatial.oracleDatabase.services.utils.GeometryUtils;
import org.locationtech.jts.geom.Point;
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

        if(requestPointDTO.isEmpty()){
            throw new IllegalArgumentException("Nome do ponto geométrico vazio");
        }


        for (RequestPointDTO requestPoint : requestPointDTO) {

            LocationSpatial point = new LocationSpatial();
            Point geometryPoint = GeometryUtils.createPoint(requestPoint.longitude(), requestPoint.latitude());

            point.setName(requestPoint.name());
            point.setCoordinate(geometryPoint);

            locationRepository.insertPointDatabase(requestPoint.name(),requestPoint.longitude(),requestPoint.latitude());
        }
    }
}
