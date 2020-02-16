package ch.wesr.ecommerce.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Custom Flyway configuration class allowing the combination of SpringBoot >= 2.1 and
 * Flyway < 5.0. Needed because SpringBoot >= 2.1 makes use of Flyway's new fluent
 * configuration mechanism which is not present in Flyway < 5.0.
 */
@ConditionalOnProperty(value = "spring.flyway.enabled", matchIfMissing = true)
@Configuration
@ConfigurationProperties(prefix = "spring")
@AutoConfigureAfter({DataSourceAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class FlywayConfig {

    /**
     * the Flyway instance properties will be injected
     * by Spring through the @ConfigurationProperties annotation
     */
    protected Flyway flyway;

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    protected Environment env;

    @Autowired
    protected DataSource dataSource;

    @Value("${spring.flyway.user:}") //hinter dem Doppelpunkt steht der Defaultwert, falls die property nicht gefunden wird
    private String flywayUser;
    @Value("${spring.flyway.password:}") //hinter dem Doppelpunkt steht der Defaultwert, falls die property nicht gefunden wird
    private String flywayPassword;
    @Value("${spring.datasource.url:}")
    private String flywayUrl;
    /**
     * Return the Flyway instance as bean and call
     * migrate (and repair if set) upon creation.f
     *
     */
    @Bean
    public Flyway flyway() {
        flyway.setDataSource(flywayUrl, flywayUser, flywayPassword);
        logger.info("Running flyway migrate");
        flyway.migrate();
        return flyway;
    }

    public Flyway getFlyway() {
        return flyway;
    }

    public void setFlyway(Flyway flyway) {
        this.flyway = flyway;
    }
}
