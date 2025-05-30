package ma.ensa.sendmsg.controller;

import ma.ensa.sendmsg.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class SmsController {
    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }
    @PostMapping("/send")
    //@RequestParam String phoneNumber,@RequestParam String message
    public ResponseEntity<Integer> send(

    ) throws IOException {
//        String phoneNumber = request.getOrDefault("phoneNumber", "");
//        return ResponseEntity.ok(smsService.sendSms(phoneNumber,message));
//        System.out.println(phoneNumber);
        System.out.println("hello sms");
        return ResponseEntity.ok(0);


    }
}
