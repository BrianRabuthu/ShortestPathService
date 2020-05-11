package za.co.discovery.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"za.co.discovery.assignment"})
@EnableJpaRepositories(basePackages="za.co.discovery.assignment.repository")
@EnableTransactionManagement
@EntityScan(basePackages="za.co.discovery.assignment.entity")
public class BrianRabuthuApplication  extends SpringBootServletInitializer{
	

	   @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(BrianRabuthuApplication.class);
	   }
    
	public static void main(String[] args) {
		SpringApplication.run(BrianRabuthuApplication.class, args);
	}
	



}
