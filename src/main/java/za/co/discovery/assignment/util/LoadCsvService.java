package za.co.discovery.assignment.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
@SpringBootConfiguration
@Component
public class LoadCsvService {
	
	@Value("classpath:planet-routes.csv")
	private Resource planetRoutesFile;
	
	@Value("classpath:planet-names.csv")
	private Resource planetNamesFile;
	
	public List<String[]> getAllPlanetRoutes(){
		try {
			File file = new ClassPathResource("planet-routes.csv").getFile();;
			CSVReader reader = new CSVReader(new FileReader(file));
		    List<String[]> allRows = reader.readAll();
		    return allRows;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<String[]> getAllPlanetNames(){
		File file;
		try {
			file = planetNamesFile.getFile();
			CSVReader reader = new CSVReader(new FileReader(file));
		    List<String[]> allRows = reader.readAll();
		    return allRows;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}

}
