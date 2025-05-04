package oracle.spatial.oracleDatabase.dtos.LineString;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para inserir rotas geométricas com N pontos")
public record RequestLineStringDTO(
        String name,
        double[] longitude,
        double[] latitude) {
}