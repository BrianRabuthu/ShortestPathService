package za.co.discovery.assignment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.entity.Planet;
import za.co.discovery.assignment.entity.PlanetRoute;
import za.co.discovery.assignment.repository.IPlanetRepository;
import za.co.discovery.assignment.repository.IPlanetRouteRepository;

@Component
@Slf4j
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private IPlanetRepository planetRepository;

    @Autowired
    private IPlanetRouteRepository planetRouteRepository;
    
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		final List<String[]> planets = getAllPlanetNames();
		final List<String[]> routes = getAllPlanetRoutes();
		
		if(CollectionUtils.isNotEmpty(planets)) {
		     for(String[] row : planets){
		    	 final Planet planet = new Planet();
		    	 planet.setPlanetNode(row[0]);
		    	 planet.setPlanetSourceName(row[1]);
		    	 planetRepository.save(planet);
		    	 log.info(planet.toString());
		      }
		}
		
		if(CollectionUtils.isNotEmpty(routes)) {
		     for(String[] row : routes){
		    	 final PlanetRoute route = new PlanetRoute();
		    	 route.setId(Long.parseLong(row[0]));
		    	 route.setPlanetOrigin(row[1]);
		    	 route.setPlanetDestination(row[2]);
		    	 route.setDistance(new BigDecimal(StringUtils.replace(row[3], ",", ".")));
		    	 route.setTraffic(new BigDecimal(StringUtils.replace(row[4], ",", ".")));   	 
		    	
		    	 planetRouteRepository.save(route);
		    	 log.info(route.toString());
		      }
		}
		
		final List<Planet> pl = (List<Planet>) planetRepository.findAll();
		log.info(String.valueOf(pl.size()));
		
	}
	
	public static List<String[]> getAllPlanetRoutes(){
		try {
			final File file = new ClassPathResource("planet-routes.csv").getFile();
			
			final CSVParser parser = new CSVParserBuilder()
				    .withSeparator('|')
				    .build();
				 
			final CSVReader csvReader = new CSVReaderBuilder(new FileReader(file))
				    .withSkipLines(1)
				    .withCSVParser(parser)
				    .build();
				
		   final  List<String[]> allRows = csvReader.readAll();
		    return allRows;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<String[]> getAllPlanetNames(){
		try {
			final File file = new ClassPathResource("planet-names.csv").getFile();
			
			final CSVParser parser = new CSVParserBuilder()
				    .withSeparator('|')
				    .build();
				 
			final CSVReader csvReader = new CSVReaderBuilder(new FileReader(file))
				    .withSkipLines(1)
				    .withCSVParser(parser)
				    .build();
			
		    List<String[]> allRows = csvReader.readAll();
		    return allRows;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}

}
