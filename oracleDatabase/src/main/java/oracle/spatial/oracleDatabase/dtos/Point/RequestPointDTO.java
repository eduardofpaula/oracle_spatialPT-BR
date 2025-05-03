package oracle.spatial.oracleDatabase.dtos.Point;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para inserir pontos geográficos")
@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestPointDTO(String name,
                              double longitude,
                              double latitude) {
}
