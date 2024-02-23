package de.kleemann.co2_hsharz.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This is a Spring Configuration Class for the persistence layer. <br>
 * It enables Entity- and Repository-Scan for the persistence layer
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("de.kleemann.co2_hsharz.persistence")
@EntityScan("de.kleemann.co2_hsharz.persistence")
public class PersistenceConfiguration {

}