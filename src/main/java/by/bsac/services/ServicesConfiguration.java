package by.bsac.services;

import by.bsac.services.accounting.UserManager;
import by.bsac.services.accounting.UserManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    Logger LOGGER = LoggerFactory.getLogger(ServicesConfiguration.class);

    public ServicesConfiguration() {
        LOGGER.info("Start to initialize " +getClass().getName() +" configuration class");
    }

    @Bean
    public UserManager createAccountManagerService() {
        return new UserManagerImpl();
    }
}
