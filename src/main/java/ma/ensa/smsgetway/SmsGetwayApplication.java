package ma.ensa.smsgetway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class SmsGetwayApplication {
    ;public final List<Integer> ports =List.of(7000,7001,7002,7003,7004,7005);

    public static void main(String[] args) {
        SpringApplication.run(SmsGetwayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        ApplicationContext context=new ClassPathXmlApplicationContext("getway.xml");
        return builder.routes()

                .route(p -> p
                        .path("")
                        .and().method("GET")

                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")

                        .setFallbackUri("forward:/fallback")
                          )
                        )
                        .uri("http://127.0.0.1:"+ports.get(0))).
                route(p -> p
                        .path("group")
                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")
                                        .setFallbackUri("forward:/fallback")
                                )
                        )
                        .uri("http://127.0.0.1:"+ports.get(1))).
                route("history",p -> p

                        .path("/history")
                        .and().method("GET")
                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")
                                        .setFallbackUri("forward:/fallback")
                                )
                                .prefixPath("/history")

                        )


                        .uri("http://127.0.0.1:7002")


                )

                .route(p -> p
                        .path("receiver")
                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")
                                        .setFallbackUri("forward:/fallback")
                                )
                        )
                        .uri("http://127.0.0.1:"+ports.get(3)))
                        .route(p -> p
                                .path("schedule")
                                .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")
                                                .setFallbackUri("forward:/fallback")
                                        )
                                )
                                .uri("http://127.0.0.1:"+ports.get(4)))
                                .route(p -> p
                                        .path("sendmsg")
                                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")
                                                        .setFallbackUri("forward:/fallback")
                                                )
                                        )
                                        .uri("http://127.0.0.1:"+ports.get(5))).
                build();
    }


}
