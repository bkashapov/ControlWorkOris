package oriscontrolwork.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.util.Properties;

@AllArgsConstructor
public class DbConfig {

    private Properties properties;

    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("database.url"));
        hikariConfig.setUsername(properties.getProperty("database.username"));
        hikariConfig.setPassword(properties.getProperty("database.password"));
        hikariConfig.setDriverClassName(properties.getProperty("database.driver-name"));
        hikariConfig.setMaximumPoolSize(
                Integer.parseInt(properties.getProperty("database.hikari.max-pool-size")));

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        System.out.println("hikariDataSource done");


        Flyway flyway = Flyway.configure().dataSource(hikariDataSource).load();
        flyway.baseline();
        flyway.migrate();
        return hikariDataSource;
    }
}
