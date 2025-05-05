package oracle.spatial.oracleDatabase.services;

import oracle.spatial.oracleDatabase.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDataService {

    @Autowired
    LocationRepository locationRepository;

}
