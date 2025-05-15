package ma.ensa.sendmsg.controller;

import ma.ensa.sendmsg.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SmsController {
    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }
    @PostMapping("/send")
    public ResponseEntity<Integer> send(@RequestParam String phoneNumber,@RequestParam String message) throws IOException {
        return ResponseEntity.ok(smsService.sendSms(phoneNumber,message));


    }
}
