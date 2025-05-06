package ma.ensa.gethitory.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin("http://127.0.0.1:7001")
public class Test {
    @GetMapping
    public String index(){
        return "Hello World";
    }

}
