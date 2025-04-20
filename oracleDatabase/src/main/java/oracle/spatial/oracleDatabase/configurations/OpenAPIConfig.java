package oracle.spatial.oracleDatabase.configurations;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Oracle Spatial",
                version = "v1",
                description = """
                        Aplicação para visualização de dados geográficos utilizando Oracle Spatial.
                        
                        Esta aplicação tem como objetivo demonstrar a utilização do Oracle Spatial para o armazenamento e manipulação de dados geográficos.
                        """,
                contact = @Contact(
                        name = "Eduardo",
                        url = "",
                        email = "eduardo.fariasp@outlook.com"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)

public class OpenAPIConfig {
}
