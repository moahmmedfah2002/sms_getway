package ma.ensa.receiver.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/receivers")
public class ReceiverController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Receiver!";
    }
}
