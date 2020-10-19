package ca.sheridancollege.diclemed.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

	// Config file for database, creates bean to connect with datasource (H2 in this case)
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource datasource) {
		
			// datasource in the connection String, it is handled by Spring
		return new NamedParameterJdbcTemplate(datasource);
	}
}
