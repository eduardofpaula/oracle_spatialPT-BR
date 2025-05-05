package oracle.spatial.oracleDatabase.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import oracle.spatial.oracleDatabase.services.GetDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
@Tag(name= "API para extração de dados geográficos", description = "Operações para extrair dados")
@CrossOrigin(origins = "*")
public class GetDataController {

    @Autowired
    GetDataService getDataService;

}
