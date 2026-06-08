package com.purvisha.portfolio_backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

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
        System.out.println("====== SYSTEM ENVIRONMENT VARIABLES DUMP ======");
        try {
            System.getenv().forEach((k, v) -> {
                String maskedValue = v;
                String upperKey = k.toUpperCase();
                if (upperKey.contains("PASS") || upperKey.contains("SECRET") || upperKey.contains("URL") || upperKey.contains("KEY") || upperKey.contains("TOKEN")) {
                    maskedValue = "[MASKED] (Length: " + (v != null ? v.length() : 0) + ")";
                }
                System.out.println(k + " = " + maskedValue);
            });
        } catch (Exception e) {
            System.out.println("Failed to dump environment variables: " + e.getMessage());
        }
        System.out.println("===============================================");

        String databaseUrl = System.getenv("DATABASE_URL");
        
        System.out.println("====== DATABASE CONFIGURATION LOGS ======");
        System.out.println("System.getenv(\"DATABASE_URL\") length: " + (databaseUrl != null ? databaseUrl.length() : "null"));
        System.out.println("defaultUrl: " + defaultUrl);
        System.out.println("defaultUsername: " + defaultUsername);
        
        if (databaseUrl != null && !databaseUrl.trim().isEmpty()) {
            databaseUrl = databaseUrl.trim();
            System.out.println("Attempting to parse DATABASE_URL...");
            try {
                String cleanUrl = databaseUrl;
                if (cleanUrl.startsWith("postgresql://")) {
                    cleanUrl = cleanUrl.substring("postgresql://".length());
                } else if (cleanUrl.startsWith("postgres://")) {
                    cleanUrl = cleanUrl.substring("postgres://".length());
                } else if (cleanUrl.startsWith("jdbc:postgresql://")) {
                    System.out.println("DATABASE_URL is already a JDBC URL. Using directly.");
                    HikariConfig hikariConfig = new HikariConfig();
                    hikariConfig.setJdbcUrl(databaseUrl);
                    hikariConfig.setUsername(defaultUsername);
                    hikariConfig.setPassword(defaultPassword);
                    hikariConfig.setDriverClassName("org.postgresql.Driver");
                    return new HikariDataSource(hikariConfig);
                } else {
                    throw new IllegalArgumentException("Unknown protocol in DATABASE_URL");
                }
                
                // Split credentials and host info at the LAST '@'
                int lastAtIndex = cleanUrl.lastIndexOf('@');
                if (lastAtIndex == -1) {
                    throw new IllegalArgumentException("Missing '@' in DATABASE_URL");
                }
                
                String credentials = cleanUrl.substring(0, lastAtIndex);
                String hostInfo = cleanUrl.substring(lastAtIndex + 1);
                
                // Parse credentials (split at first ':')
                String username = "";
                String password = "";
                int firstColonIndex = credentials.indexOf(':');
                if (firstColonIndex != -1) {
                    username = credentials.substring(0, firstColonIndex);
                    password = credentials.substring(firstColonIndex + 1);
                } else {
                    username = credentials;
                }
                
                // Parse host and database (split at first '/')
                int firstSlashIndex = hostInfo.indexOf('/');
                if (firstSlashIndex == -1) {
                    throw new IllegalArgumentException("Missing database name in DATABASE_URL");
                }
                
                String hostPort = hostInfo.substring(0, firstSlashIndex);
                String databaseName = hostInfo.substring(firstSlashIndex + 1);
                
                // Parse host and port (split at first ':')
                String host = "";
                String port = "5432";
                int portColonIndex = hostPort.indexOf(':');
                if (portColonIndex != -1) {
                    host = hostPort.substring(0, portColonIndex);
                    port = hostPort.substring(portColonIndex + 1);
                } else {
                    host = hostPort;
                }
                
                // If there are query parameters (like sslmode), preserve them
                String dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
                System.out.println("Successfully parsed DATABASE_URL!");
                System.out.println("Parsed JDBC URL: " + dbUrl);
                System.out.println("Parsed Username: " + username);
                
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(dbUrl);
                hikariConfig.setUsername(username);
                hikariConfig.setPassword(password);
                hikariConfig.setDriverClassName("org.postgresql.Driver");
                
                return new HikariDataSource(hikariConfig);
            } catch (Exception e) {
                System.err.println("Failed to parse DATABASE_URL! Exception details:");
                e.printStackTrace();
            }
        }
        
        System.out.println("DATABASE_URL is not present or parsing failed. Falling back to default URL.");
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(defaultUrl);
        hikariConfig.setUsername(defaultUsername);
        hikariConfig.setPassword(defaultPassword);
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(hikariConfig);
    }
}
