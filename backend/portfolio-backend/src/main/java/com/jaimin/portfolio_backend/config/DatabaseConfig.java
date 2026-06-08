package com.jaimin.portfolio_backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String defaultUrl;

    @Value("${spring.datasource.username}")
    private String defaultUsername;

    @Value("${spring.datasource.password}")
    private String defaultPassword;

    @Bean
    @Primary
    public DataSource dataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        if (databaseUrl != null && !databaseUrl.trim().isEmpty() && 
            (databaseUrl.startsWith("postgres://") || databaseUrl.startsWith("postgresql://"))) {
            try {
                databaseUrl = databaseUrl.trim();
                URI dbUri = new URI(databaseUrl);
                
                String username = "";
                String password = "";
                if (dbUri.getUserInfo() != null) {
                    String[] userInfo = dbUri.getUserInfo().split(":");
                    if (userInfo.length > 0) username = userInfo[0];
                    if (userInfo.length > 1) password = userInfo[1];
                }
                
                // Construct standard JDBC url
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
                
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(dbUrl);
                hikariConfig.setUsername(username);
                hikariConfig.setPassword(password);
                hikariConfig.setDriverClassName("org.postgresql.Driver");
                
                return new HikariDataSource(hikariConfig);
            } catch (Exception e) {
                // If parsing fails, fall back to default configuration below
            }
        }
        
        // Fallback to application.properties config
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(defaultUrl);
        hikariConfig.setUsername(defaultUsername);
        hikariConfig.setPassword(defaultPassword);
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(hikariConfig);
    }
}
