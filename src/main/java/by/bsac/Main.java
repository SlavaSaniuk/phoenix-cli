package by.bsac;

import by.bsac.models.User;
import by.bsac.services.accounting.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties({
        by.bsac.properties.PersistenceProperties.class //Persistence properties
})
@Import({
        by.bsac.configuration.PersistenceGonfig.class, //Persistence configuration class
        by.bsac.services.ServicesConfiguration.class //Services configuration class
})
public class Main implements CommandLineRunner {

    private static ApplicationContext application_context;
    private static final Logger LOGGER = LoggerFactory.getLogger(by.bsac.Main.class); //Logger

    @Autowired
    AccountManager manager;

    public static void main(String[] args) {

        //Set system property
        //Hibernate will use slf4j logging instead jboss.logging
        System.setProperty("org.jboss.logging.provider", "slf4j");

        //Create SpringApplication object
        LOGGER.info("Create application object.");
        SpringApplication application = new SpringApplication(by.bsac.Main.class);

        //Set application type to CLI
        LOGGER.info("Disable web application type.");
        application.setWebApplicationType(WebApplicationType.NONE);

        //Disable banner
        LOGGER.info("Disable spring banner.");
        application.setBannerMode(Banner.Mode.OFF);

        //Create application context
        //And
        //Run application
        LOGGER.info("Create root application context and start beans initialization..");
        application_context = application.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User();
        manager.registerAccount(u1);

        User u2 = new User();
        u2.setUserIdAlias("SlavaSaniuk2");
        manager.registerAccount(u2);

        manager.deleteAccount(u1);
        manager.deleteAccount(u2);
    }
}
