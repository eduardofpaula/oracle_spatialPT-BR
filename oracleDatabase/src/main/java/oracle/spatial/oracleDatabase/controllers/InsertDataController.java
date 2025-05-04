package oracle.spatial.oracleDatabase.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import oracle.spatial.oracleDatabase.dtos.LineString.RequestLineStringDTO;
import oracle.spatial.oracleDatabase.dtos.Point.RequestPointDTO;
import oracle.spatial.oracleDatabase.dtos.ResponseDefault;
import oracle.spatial.oracleDatabase.services.InsertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name= "API para inserção de dados geográficos", description = "Operações para inserir dados")
@CrossOrigin(origins = "*")
public class InsertDataController {

    @Autowired
    private InsertDataService insertDataService;

    @PostMapping("/point")
    public ResponseEntity<ResponseDefault> insertPoint(@RequestBody List<RequestPointDTO> requestPointDTO) {
        insertDataService.insertPoint(requestPointDTO);
        return ResponseEntity.ok(new ResponseDefault("Ponto inserido com sucesso"));
    }

    @PostMapping("/linestring")
    public ResponseEntity<ResponseDefault> insertLinestring(@RequestBody List<RequestLineStringDTO> requestLineStringDTO) {
        insertDataService.inserirLineString(requestLineStringDTO);
        return ResponseEntity.ok(new ResponseDefault("Rota inserida com sucesso"));
    }

}
