package ma.ensa.smsgetway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.List;

@SpringBootApplication

public class SmsGetwayApplication {
    ;public final List<Integer> ports =List.of(7000,7001,7002,7003,7004,7005);

    public static void main(String[] args) {
        SpringApplication.run(SmsGetwayApplication.class, args);
    }



}
