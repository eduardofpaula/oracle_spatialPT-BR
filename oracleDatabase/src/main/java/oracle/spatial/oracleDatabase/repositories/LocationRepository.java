package oracle.spatial.oracleDatabase.repositories;

import oracle.spatial.oracleDatabase.entities.LocationSpatial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationSpatial, Integer> {

    @Modifying
    @Query(value = """
                INSERT INTO locationspatial (NAME, COORDINATE) 
                VALUES (
                ?1,
                SDO_GEOMETRY(
                2001,4326,
                SDO_POINT_TYPE(?2,?3,NULL),
                NULL,
                NULL
                )
            )
            """, nativeQuery = true)
    void insertPointDatabase(String name, double longitude, double latitude);
}
