package by.bsac.configuration;

import by.bsac.properties.PersistenceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration class that's create a DataSource and manage JPA / Hibernate
 * configuration properties. Needed to manually import in main class (by.bsac.Main.java)
 * with @Import annotation.
 * Class creates a  {@link javax.sql.DataSource} based on Spring {@link DriverManagerDataSource} object
 * and configure them.  You need to explicit set all parameters in application.properties file in section
 * "phoenix.persistence.datasources.*".
 * Next, configuration class create a JPA EntityManager factory bean which used as factory
 * for getting shared entity managers.
 * Also spring framework will be used for handing database transaction. Configuration class create PlatformTransactionManager bean.
 */
@EnableTransactionManagement
public class PersistenceGonfig {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(by.bsac.configuration.PersistenceGonfig.class); //Logger
    //Spring beans
    private PersistenceProperties persistence_properties; //Persistence configuration properties (autowired)

    //Default constructor
    public PersistenceGonfig() {
        //Log
        LOGGER.info("Persistence configuration class start to initialize persistence beans " +
                "and configure JPA / Hibernate properties");
    }

    /**
     * Datasource for development purposes. Bean is implemented by Spring {@link DriverManagerDataSource} class.
     * Datasource can be configured by properties defined in application.properties file persistence section. Each property started with key
     * "phoenix.persistence.dotasources.*".
     * @return - Configured {@link DataSource} object.
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource() {

        //Create datasource
        DriverManagerDataSource ds = new DriverManagerDataSource();

        //Log
        LOGGER.debug("Create DriverManager datasource object");

        //Set properties
       ds.setDriverClassName(persistence_properties.getDatasources().getDriver_class_name());
       ds.setUrl("jdbc:mysql://localhost:36548/" +persistence_properties.getDatasources().getDatabase().get(0));
       ds.setUsername(persistence_properties.getDatasources().getUser_name());
       ds.setPassword(persistence_properties.getDatasources().getUser_password());

       //Log
        LOGGER.info("Return configured datasource with link on database: " +persistence_properties.getDatasources().getDatabase().get(0));

       //Return configured datasource
       return ds;
    }

    /**
     * {@link EntityManagerFactory} bean used for getting shared entity managers.
     * EntityManagers used for simple connection with datasource. EntityManagerFactory bean has a JPA structure.
     * JPA used Hibernate as default it's provider. Bean used hibernate configuration properties defined
     * in application.properties file. Each configuration property started with  "phoenix.persistence.hiber.*" key.
     * @param ds - Configured {@link DataSource}. Datasource is autowired.
     * @return - Configured {@link LocalContainerEntityManagerFactoryBean}.
     */
    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean createEntityFactoryBean(DataSource ds) {

        //Create entity manager factory
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        //Set datasource
        emf.setDataSource(ds);

        //Set JPA provider
        HibernateJpaVendorAdapter provider = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(provider);

        //Set packages to scan
        emf.setPackagesToScan("by.bsac.models");

        //Set Hibernate properties / log
        LOGGER.info("Set Hibernate configuration properties to EntityManager factory.");
        emf.setJpaProperties(this.getHibernateConfigurationProperties());

        //Return emf
        return emf;
    }

    /**
     * Transaction manager automatic handed JDBC transaction. {@link PlatformTransactionManager} based on
     * {@link JpaTransactionManager} implementation. Bean required a EntityManagerFactory which is autowired by Spring framework.
     * @param emf - Configured {@link EntityManagerFactory};
     * @return - {@link PlatformTransactionManager} bean.
     */
    @Bean
    public PlatformTransactionManager createTransactionManager(EntityManagerFactory emf) {

        //Log
        LOGGER.info("Create a transaction manager bean (PlatformTransactionManager).");
        //JPA
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        return tm;
    }

    /*
     * Load Hibernate configuration properties from application.properties file via PersistenceProperties bean.
     */
    private Properties getHibernateConfigurationProperties() {

        //Create properties object / log
        LOGGER.debug("Start to load hibernate configuration properties.");
        Properties props = new Properties();

        //Load properties
        props.put("hibernate.dialect",this.persistence_properties.getHiber().getDialect());
        props.put("hibernate.show_sql", String.valueOf(this.persistence_properties.getHiber().isShow_sql()));
        props.put("hibernate.format_sql", String.valueOf(this.persistence_properties.getHiber().isFormat_sql()));
        props.put("hibernate.default_entity_mode", this.persistence_properties.getHiber().getDefault_entity_mode());
        props.put("hibernate.generate_statistics", String.valueOf(this.persistence_properties.getHiber().isGenerate_statistic()));
        props.put("hibernate.connection.autocommit", String.valueOf(this.persistence_properties.getHiber().isConnection_autocommit()));
        props.put("hibernate.hbm2ddl.auto", this.persistence_properties.getHiber().getHbm2ddl_auto());

        //Return configuration properties
        return props;
    }

    //Spring autowiring
    @Autowired
    private void autowire(PersistenceProperties properties) {
        this.persistence_properties = properties;
    }

}
