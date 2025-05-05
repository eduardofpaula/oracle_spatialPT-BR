package oracle.spatial.oracleDatabase.repositories;

import oracle.spatial.oracleDatabase.entities.LocationSpatial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationSpatial, Integer> {

}
