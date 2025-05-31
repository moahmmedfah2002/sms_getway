package ma.ensa.confserver;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@SpringBootApplication
@EnableConfigServer
public class ConfServerApplication {

    public static void main(String[] args) {
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ClassPathResource("application.yml"));
        Properties properties = yamlFactory.getObject();
        if(properties!=null){

            System.out.println(properties.getProperty("spring.cloud.config.server.git.uri"));
        }else{
            System.out.println("properties is null");
        }
        SpringApplication.run(ConfServerApplication.class, args);
    }


}
