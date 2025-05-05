package oracle.spatial.oracleDatabase.dtos.Point;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Retorna os pontos de parada no banco de dados")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponsePointDTO(
        String name,
        double[] longitude,
        double[] latitude){
}
