package by.bsac;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static ApplicationContext application_context;

    public static void main(String[] args) {

        //Create SpringApplication object
        SpringApplication application = new SpringApplication(by.bsac.Main.class);

        //Set application type to CLI
        application.setWebApplicationType(WebApplicationType.NONE);

        //Disable banner
        application.setBannerMode(Banner.Mode.OFF);

        //Create application context
        //And
        //Run application
        application_context = application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
