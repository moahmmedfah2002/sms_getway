package ma.ensa.smsgetway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Feedback {
    @RequestMapping("/fallback")
    public Mono<String> getFeedback() {
        return Mono.just("feedback");
    }
}
