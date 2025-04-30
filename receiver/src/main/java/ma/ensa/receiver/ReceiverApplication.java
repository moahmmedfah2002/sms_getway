package ma.ensa.receiver;

import lombok.AllArgsConstructor;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.repository.ReceiverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
public class ReceiverApplication {
    private final ReceiverRepository receiverRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            ReceiverRepository receiverRepository
    ) {
        String userId = "ff5aef91-c268-43cb-bf3b-e4ff6c2f1539"; // real id of admin user in keycloak
        return args -> {
            // insert list of random receivers with builder
            for (long i = 0; i < 10; i++) {
                Receiver receiver = Receiver.builder()
                        .name("Receiver " + i)
                        .phoneNumber("060000000" + i)
                        .userId(userId)
                        .build();
                receiverRepository.save(receiver);
            }
        };
    }

}
