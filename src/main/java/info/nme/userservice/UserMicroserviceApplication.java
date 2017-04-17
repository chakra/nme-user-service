package info.nme.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("userservice.properties")
public class UserMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                new Object[]{
                        UserMicroserviceApplication.class
                        //,
                       // DevelopmentProfileConfiguration.class
                }
                , args);
    }
}
