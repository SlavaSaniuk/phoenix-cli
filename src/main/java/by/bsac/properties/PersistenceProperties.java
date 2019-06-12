package by.bsac.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Class load configuration properties from application.properties file.
 * This class contains inners static class that's represent nested persistence properties for
 * datasource and JPA/Hibernate technique.
 */
@ConfigurationProperties(prefix = "phoenix.persistence")
public class PersistenceProperties {

    //Logger
    private final Logger LOGGER = LoggerFactory.getLogger(by.bsac.properties.PersistenceProperties.class);

    //Persistence configuration properties
    private final Datasources datasources = new Datasources();
    private final Hiber hiber = new Hiber();

    //Constructor
    public PersistenceProperties() {
        //Log
        LOGGER.info("Read and apply 'PERSISTENCE'' configuration properties from 'application.properties' file:");
    }

    /**
     * Class has a fields that contains datasource configuration properties.
     */
    public static class Datasources {

        //Logger
        private final Logger LOGGER = LoggerFactory.getLogger(by.bsac.properties.PersistenceProperties.Datasources.class);

        //Datasources properties
        private final List<String> database = new ArrayList<>(); //List of databases names
        private String driver_class_name; //Vendor specific driver class name
        private String user_name; //User name
        private String user_password; //User password

        public List<String> getDatabase() {
            //Log
            LOGGER.debug("Number of usages databases: " +database.size());
            return database;
        }
        public String getDriver_class_name() {
            return driver_class_name;
        }
        public void setDriver_class_name(String driver_class_name) {
            if (driver_class_name == null || driver_class_name.isEmpty()) {
            //Warning
            LOGGER.warn("Driver class name is not set, we will use default mysql driver");
            this.driver_class_name = "com.mysql.cj.jdbc.Driver";
            return;
        }
            //Log
            LOGGER.debug("Use vendor database driver: " +driver_class_name);
            this.driver_class_name = driver_class_name;
        }
        public String getUser_name() {            return user_name;        }
        public void setUser_name(String user_name) {
            //Log
            LOGGER.debug("Set datasource user name property to: " +user_name);
            this.user_name = user_name;        }
        public String getUser_password() {            return user_password;        }
        public void setUser_password(String user_password) {
            //Log
            LOGGER.debug("Set 'user_password' property to value:" +user_password);
            this.user_password = user_password;        }
    }

    /**
     * Class has a fields that contains hibernate configuration properties.
     */
    public static class Hiber {

        //Logger
        private final Logger LOGGER = LoggerFactory.getLogger(by.bsac.properties.PersistenceProperties.Hiber.class);
        //Hibernate properties
        private String dialect; //Hibernate dialect
        private boolean show_sql; //Show SQL flag
        private boolean format_sql; //Format SQL flag
        private String default_entity_mode; //Default entity mode
        private boolean generate_statistic; //Statistic generation flag
        private boolean connection_autocommit; //Statements autocommit flag
        private String hbm2ddl_auto; //DDl generation mode

        //Getters / Setters
        public String getDialect() {
            //log
            this.LOGGER.debug("Hibernate dialect set to " +dialect);
            return dialect;
        }
        public void setDialect(String dialect) {
            if (dialect == null || dialect.equals("")) {
                //log
                this.LOGGER.warn("Hibernate dialect is not set. Hibernate will use default mysql dialect.");
                this.dialect = "org.hibernate.dialect.MySQLDialect";
                return;
            }
            this.dialect = dialect;
        }
        public boolean isShow_sql() {
            //Log
            this.LOGGER.debug("Show SQL flag set to" +show_sql);
            return show_sql;
        }
        public void setShow_sql(boolean show_sql) {
            //Log
            this.LOGGER.debug("Set show SQL flag to " +show_sql);
            this.show_sql = show_sql;
        }
        public boolean isFormat_sql() {
            //Log
            this.LOGGER.debug("Format SQL flag set to" +format_sql);
            return format_sql;
        }
        public void setFormat_sql(boolean format_sql) {
            //Log
            this.LOGGER.debug("Set format SQL flag to " +format_sql);
            this.format_sql = format_sql;
        }
        public String getDefault_entity_mode() {
            //Log
            this.LOGGER.debug("Default hibernate entity mode is " +default_entity_mode);
            return default_entity_mode;
        }
        public void setDefault_entity_mode(String default_entity_mode) {
            if (default_entity_mode == null || default_entity_mode.equals("")) {
                //Log
                this.LOGGER.warn("Hibernate default entity mode is not set. Use POJO (default) value ");
                this.default_entity_mode = "pojo";
                return;
            }
            this.default_entity_mode = default_entity_mode;
        }
        public boolean isGenerate_statistic() {
            //Log
            this.LOGGER.debug("Generate statistic flag set to" +generate_statistic);
            return generate_statistic;
        }
        public void setGenerate_statistic(boolean generate_statistic) {
            //Log
            this.LOGGER.debug("Generate statistic flag to " +generate_statistic);
            this.generate_statistic = generate_statistic;
        }
        public boolean isConnection_autocommit() {
            //Log
            this.LOGGER.debug("Connection autocommit flag set to" +connection_autocommit);
            return connection_autocommit;
        }
        public void setConnection_autocommit(boolean connection_autocommit) {
            //Log
            this.LOGGER.debug("Set connection autocommit flag to " +connection_autocommit);
            this.connection_autocommit = connection_autocommit;
        }
        public String getHbm2ddl_auto() {
            //log
            this.LOGGER.debug("HBM2DDL generation mode set to " +hbm2ddl_auto);
            return hbm2ddl_auto;
        }
        public void setHbm2ddl_auto(String hbm2ddl_auto) {
            if (hbm2ddl_auto == null || hbm2ddl_auto.equals("")) {
                //log
                this.LOGGER.warn("Hibernate HBM2DLL mode is not set. Hibernate will use default \' CREATE\' mode.");
                this.hbm2ddl_auto = "create";
                return;
            }
            this.hbm2ddl_auto = hbm2ddl_auto;
        }
    }

    //Getters / Setters
    public Datasources getDatasources() {
        return datasources;
    }
    public Hiber getHiber(){
        return this.hiber;
    }

}
