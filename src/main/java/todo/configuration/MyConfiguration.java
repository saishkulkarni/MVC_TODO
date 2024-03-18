package todo.configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("todo")
public class MyConfiguration {

	@Bean
	public EntityManager get() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}
}
