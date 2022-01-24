package by.overone.online_shop.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan("by.overone.online_shop")
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig("/db.properties");
        System.out.println(config.getMinimumIdle());
        return new HikariDataSource(config);
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public TransactionManager transactionManager(DataSource dataSource){
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource);
//        return dataSourceTransactionManager;
//    }

//    @Bean
//    public ViewResolver internalResourseViewResolver(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/view/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("by.overone.online_shop.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }



    Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hdm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }

}
