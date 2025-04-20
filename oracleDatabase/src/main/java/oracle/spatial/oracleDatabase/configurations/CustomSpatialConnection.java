package oracle.spatial.oracleDatabase.configurations;

import com.zaxxer.hikari.pool.HikariProxyConnection;
import oracle.jdbc.driver.OracleConnection;
import org.geolatte.geom.codec.db.oracle.ConnectionFinder;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomSpatialConnection implements ConnectionFinder {

    @Override
    public Connection find(Connection connection){
        try {
            return ((HikariProxyConnection) connection).unwrap(OracleConnection.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
