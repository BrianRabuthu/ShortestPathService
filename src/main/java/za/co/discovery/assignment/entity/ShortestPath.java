package za.co.discovery.assignment.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ShortestPath {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String planetNode;
	private String planetName;
	private String path;
}
