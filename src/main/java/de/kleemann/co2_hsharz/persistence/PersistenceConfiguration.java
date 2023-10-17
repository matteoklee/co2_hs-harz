package de.kleemann.co2_hsharz.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EntityScan("de.kleemann.co2_hsharz.persistence")
@EnableTransactionManagement
//@EnableJpaRepositories("de.kleemann.co2_hsharz.persistence")
@EnableJpaRepositories("de.kleemann.co2_hsharz.persistence")
//@ComponentScan(basePackages = { "de.kleemann.co2_hsharz.persistence" })
@EntityScan("de.kleemann.co2_hsharz.persistence")
public class PersistenceConfiguration {

}