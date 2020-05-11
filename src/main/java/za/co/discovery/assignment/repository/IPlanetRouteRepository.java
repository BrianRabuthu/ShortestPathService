package za.co.discovery.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import za.co.discovery.assignment.entity.PlanetRoute;

public interface IPlanetRouteRepository extends CrudRepository<PlanetRoute,Long>{
    @Query(value = "SELECT pr FROM PlanetRoute pr WHERE pr.planetOrigin LIKE '%' || :keyword || '%'"
            + " OR pr.planetDestination LIKE '%' || :keyword || '%'"
            + " OR pr.distance LIKE '%' || :keyword || '%'")
    public List<PlanetRoute> search(@Param("keyword") String keyword);
}
