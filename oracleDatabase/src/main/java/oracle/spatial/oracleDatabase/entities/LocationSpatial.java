package oracle.spatial.oracleDatabase.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "LOCATIONSPATIAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationSpatial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationspatial_seq_gen")
    @SequenceGenerator(name = "locationspatial_seq_gen",sequenceName = "locationspatial_seq",allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "COORDINATE", columnDefinition = "SDO_GEOMETRY")
    private Geometry coordinate;
}
