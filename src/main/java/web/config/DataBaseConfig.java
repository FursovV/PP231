package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(value = "web")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DataBaseConfig {

   private final Environment env;

   public DataBaseConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
      dataSource.setUrl(env.getRequiredProperty("db.url"));
      dataSource.setUsername(env.getRequiredProperty("db.username"));
      dataSource.setPassword(env.getRequiredProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
      entityManager.setDataSource(getDataSource());
      entityManager.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
      entityManager.setJpaVendorAdapter(getJpaVendor());
      entityManager.setJpaProperties(getHibernateProperties());
      return entityManager;
   }

   @Bean
   public JpaVendorAdapter getJpaVendor() {
      HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
      jpaVendorAdapter.setDatabase(Database.MYSQL);
      jpaVendorAdapter.setGenerateDdl(true);
      jpaVendorAdapter.setShowSql(true);
      return jpaVendorAdapter;
   }

   @Bean
   public Properties getHibernateProperties() {
      Properties props = new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql", "true"));
      props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql", "true"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "update"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"));
      props.put("hibernate.connection.charSet", "UTF-8");
      return props;
   }

   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory em) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(em);
      return transactionManager;
   }
}
