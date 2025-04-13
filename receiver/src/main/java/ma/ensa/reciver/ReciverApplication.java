package ma.ensa.reciver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReciverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReciverApplication.class, args);
    }

}
