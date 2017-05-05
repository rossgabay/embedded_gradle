
package com.rgabay.embedded_gradle;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
public class Application {

	private final static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    @Transactional
	CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {

			Person greg = new Person("Greg");

			personRepository.save(greg);

			Person gregz = personRepository.findByName(greg.getName());
            log.info("Sanity check, greg's name is: {}", gregz.getName());

            gregz.hasPet(new Pet("Garfield"));
            gregz.hasPet(new Pet("Tom"));
            gregz.setFavorite_pet(new Pet("Bonnie"));

            personRepository.save(gregz);

            gregz = personRepository.findOne(greg.getId(), -1);
            gregz.pets.forEach(p-> log.info("Greg's has a pet - {}", p.getName()));

		};
	}

    // embedded driver config example, to make the db non-permanent, remove .setURI call
    /*
	@Bean
    public Configuration configuration() {
        Configuration config = new Configuration();
        config
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
                .setURI("file:///var/tmp/graph.db");
        return config;
    }*/

	// HTTP driver config example

	@Bean
	public Configuration configuration() {
		Configuration config = new Configuration();
		config
				.driverConfiguration()
				.setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
				.setURI("http://localhost:7474");
		return config;
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), "com.rgabay.embedded_gradle");
	}

    @Bean
    public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }

}
