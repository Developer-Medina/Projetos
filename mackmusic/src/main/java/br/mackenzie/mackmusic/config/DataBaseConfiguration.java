package br.mackenzie.mackmusic.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataBaseConfiguration { //Substituiremos a classe padrão do Spring para banco de dados com uma nossa

    @Value("${spring.datasource.url}") //Porque ele vai puxar de lá
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driver-class-name}") //kebab casing
    String driver;

    //Criando DataSource
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setPoolName("mackmusic");
        config.setMaxLifetime(6000000);
        config.setConnectionTimeout(10000);

        return new HikariDataSource(config);
    }



}
