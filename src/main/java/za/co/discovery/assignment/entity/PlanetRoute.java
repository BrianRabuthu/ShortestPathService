package za.co.discovery.assignment.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PlanetRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String planetOrigin;
    private String planetDestination;
    private BigDecimal distance;
    private BigDecimal traffic;
    private String path;
}
