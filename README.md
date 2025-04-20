# oracle_spatialPT-BR
Estudo sobre como funciona Oracle Spatial no Oracle Database 

# Oracle Spatial
Oracle Spatial é uma opção do Oracle Database que fornece suporte para dados espaciais e geográficos. Ele permite armazenar, consultar e analisar dados espaciais de diversos tipos diferentes, além de realizar operações espaciais complexas.

![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

# Ambiente utilizado
- Oracle Database 19c
- Oracle SQL Developer 24.3.1.347
- Java 21

# Tipos de dados espaciais suportados
- Point
- LineString
- Polygon
- Arc Line String
- Arc Polygon
- Compound Polygon
- Compound Line String
- Circle
- Retangle

![forms](https://github.com/user-attachments/assets/75b51130-0156-4aef-9070-0a1f5edb6795)

# Sobre o objeto Spatial
O Spatial funciona através de um tipo de objeto chamado SDO_GEOMETRY, que é um tipo de dado espacial que pode armazenar diferentes tipos de geometria, como pontos, linhas e polígonos...
O SDO_GEOMETRY é um tipo de dado complexo que contém informações sobre a geometria, como o tipo de geometria, as coordenadas e o sistema de referência espacial (SRS).

## Construção do objeto SDO_GEOMETRY
```sql
SDO_GEOMETRY (
    <SDO_GTYPE>, -- id que representa o tipo de geometria
    <SDO_SRID>, -- id que representa o sistema de referência espacial
    <SDO_POINT_TYPE> -- usado para pontos, 'Null' para outros tipos
    <SDO_ELEM_INFO_ARRAY>, -- usado para definir a estrutura da geometria
    <SDO_ORDINATE_ARRAY> -- lista de coordenadas que definem a geometria
)
```
### SDO_GTYPE:
O SDO_GTYPE é um número inteiro que representa o tipo de geometria e dimensionalidade.
O SDO_GTYPE é composto por 4 digitos no formato DLTT, onde:
- D = numero de dimensões (2D, 3D, 4D)
- L = referencia linear para geometria tridimensional, 0 para 2D
- T = tipo de geometria 
- [Mais detalhes](https://docs.oracle.com/en/database/oracle/oracle-database/23/spatl/sdo_geometry-object-type.html#GUID-755C1714-D31E-49C5-BADE-1CF3C52868B6)

### SDO_SRID:
O SDO_SRID é um número inteiro que representa o sistema de referência espacial (SRS) usado para a geometria. O SRS define como as coordenadas da geometria são interpretadas em relação à superfície da Terra.
- Por padrão, o SDO_SRID é 0, que representa um sistema de coordenadas cartesiano plano.
- [Tipos de SRS](https://docs.oracle.com/en/database/oracle/oracle-database/23/spatl/coordinate-systems-spatial-reference-systems.html)

### SDO_POINT_TYPE:
Usado somente para geometria do tipo ponto, é um array que contém as coordenadas do ponto. Para outros tipos de geometria, deve ser definido como NULL.
```sql
SDO_POINT_TYPE (
    <X>, -- longitude (coordenada x)
    <Y>, -- latitude (coordenada y)
    <Z> -- altitude (coordenada z), opcional
)
```

### SDO_ELEM_INFO_ARRAY:
O SDO_ELEM_INFO_ARRAY é um array que define a estrutura da geometria. Ele contém informações sobre como as coordenadas da geometria estão organizadas e como elas devem ser interpretadas.
- Para geometria do tipo ponto, o SDO_ELEM_INFO_ARRAY deve ser NULL.
```sql
SDO_ELEM_INFO_ARRAY (
    <SDO_STARTING_OFFSET>, -- posição inicial da geometria
    <SDO_ELEM_TYPE>, -- tipo de elemento (ponto, linha, polígono)
    <SDO_INTERPRETATION> -- interpretação do elemento (ex: linear, planar)
)
```
### SDO_ORDINATE_ARRAY:
O SDO_ORDINATE_ARRAY é um array que contém as coordenadas da geometria. As coordenadas são armazenadas em pares (x, y) para geometria 2D e em trios (x, y, z) para geometria 3D.
```sql
SDO_ORDINATE_ARRAY (
    <X1>, <Y1>, -- coordenadas do primeiro ponto
    <X2>, <Y2>, -- coordenadas do segundo ponto
    ...
)
```

# Exemplo de uso
```sql
-- Criando uma tabela para armazenar dados espaciais
CREATE TABLE localizacao (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(50),
    geom SDO_GEOMETRY
);
-- Inserindo ponto
INSERT INTO localizacao (id, nome, geom) VALUES (
    1,
    'Tesouro',
    SDO_GEOMETRY(
        2001, -- 2D Point
        4326, -- WGS84
        SDO_POINT_TYPE(10, 20, NULL), -- coordenadas do ponto
        NULL, -- SDO_ELEM_INFO_ARRAY
        NULL -- SDO_ORDINATE_ARRAY
    )
);
-- Inserindo linha
INSERT INTO localizacao (id, nome, geom) VALUES (
    2,
    'Casa pro shooping',
    SDO_GEOMETRY(
        2002, -- 2D LineString
        4326, -- WGS84
        NULL, -- SDO_POINT_TYPE
        SDO_ELEM_INFO_ARRAY(1, 2, 1), -- segmento de linhas retas
        SDO_ORDINATE_ARRAY(10, 20, 30, 40) -- array com trajeto
    )
);
-- Inserindo polígono
INSERT INTO localizacao (id, nome, geom) VALUES (
    3,
    'Casa',
    SDO_GEOMETRY(
        2003, -- 2D Polygon
        4326, -- WGS84
        NULL, -- SDO_POINT_TYPE
        SDO_ELEM_INFO_ARRAY(1, 2, 1), -- segmento de linhas retas
        SDO_ORDINATE_ARRAY(10, 20, 30, 40, 10, 20) -- array com coordenadas do polígono
    )
);
```

