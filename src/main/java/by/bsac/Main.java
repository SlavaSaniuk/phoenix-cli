package by.bsac;

import by.bsac.models.Account;
import by.bsac.models.User;
import by.bsac.repositories.AccountRepository;
import by.bsac.repositories.UserDao;
import by.bsac.repositories.UserRepository;
import by.bsac.services.accounting.UserManager;
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
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

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
    UserManager manager;



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

        Account a = new Account();
        this.manager.registerAccount(a);


    }
}
