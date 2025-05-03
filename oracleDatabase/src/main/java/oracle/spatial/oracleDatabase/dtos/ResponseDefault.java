package oracle.spatial.oracleDatabase.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para resposta padrão da API")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseDefault(String message) {
}
