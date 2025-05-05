package oracle.spatial.oracleDatabase.repositories;

import oracle.spatial.oracleDatabase.entities.LocationSpatial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationSpatial, Integer> {

    @Modifying
    @Query(value = """
            INSERT INTO locationspatial (id, name, coordinate)
            VALUES (locationspatial_seq.NEXTVAL, :name,
                SDO_GEOMETRY(
                    2001, 4326,
                    SDO_POINT_TYPE(:longitude, :latitude, NULL),
                    NULL, NULL
                )
            )
            """, nativeQuery = true)
    void insertPoint(@Param("name") String name,
                     @Param("longitude") double longitude,
                     @Param("latitude") double latitude);

    @Modifying
    @Query(value = """
            INSERT INTO locationspatial (id, name, coordinate)
            VALUES (locationspatial_seq.NEXTVAL, :name,
                SDO_GEOMETRY(
                    2002, 4326, NULL,
                    SDO_ELEM_INFO_ARRAY(1, 2, 1),
                    SDO_ORDINATE_ARRAY(:ordinates)
                )
            )
            """, nativeQuery = true)
    void insertLineString(@Param("name") String name,
                          @Param("ordinates") Double[] ordinates);


    @Modifying
    @Query(value = """
            INSERT INTO locationspatial (id, name, coordinate)
            VALUES (locationspatial_seq.NEXTVAL, :name,
                SDO_GEOMETRY(
                    2003, 4326, NULL,
                    SDO_ELEM_INFO_ARRAY(1, 1003, 1),
                    SDO_ORDINATE_ARRAY(:ordinates)
                )
            )
            """, nativeQuery = true)
    void insertPolygon(@Param("name") String name,
                       @Param("ordinates") Double[] ordinates);

}
