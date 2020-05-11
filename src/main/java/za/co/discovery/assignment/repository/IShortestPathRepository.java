package za.co.discovery.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.discovery.assignment.entity.PlanetRoute;
import za.co.discovery.assignment.entity.ShortestPath;

public interface IShortestPathRepository extends CrudRepository<ShortestPath,Long>{

}
