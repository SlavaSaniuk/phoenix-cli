package by.bsac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static ApplicationContext application_context;
    private static final Logger LOGGER = LoggerFactory.getLogger(by.bsac.Main.class); //Logger

    public static void main(String[] args) {


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
        LOGGER.info("Create root application context and run application.");
        application_context = application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
