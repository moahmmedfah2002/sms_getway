package ma.ensa.receiver;

import lombok.AllArgsConstructor;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.repository.ReceiverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
@EnableFeignClients
public class ReceiverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            ReceiverRepository receiverRepository
    ) {
//        String userId = "5b2f85df-b0a8-4b12-b1f5-e222d79fc825"; // real id of admin user in keycloak
        long userId = 3;
        return args -> {
            // insert list of random receivers with builder
            for (long i = 0; i < 10; i++) {
                Receiver receiver = Receiver.builder()
                        .name("Receiver " + i)
                        .phoneNumber("4200210434" + i)
                        .userId(userId)
                        .build();
                receiverRepository.save(receiver);
            }
        };
    }

}
