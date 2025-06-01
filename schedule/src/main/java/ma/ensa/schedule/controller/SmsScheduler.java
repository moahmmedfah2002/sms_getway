package ma.ensa.schedule.controller;


import ma.ensa.schedule.openFeaign.SmsFeign;
import ma.ensa.schedule.service.SmsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController

public class SmsScheduler {


    private SmsService smsService;

    public SmsScheduler(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping
    public ResponseEntity<Integer> schedule(@RequestBody Map<String, Object> body ) {

        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", "23423423432");
        map.put("label", "23423423432");
        map.put("message", "23423423432");

        try {
            smsService.sendSms(map.get("phoneNumber"),map.get("message"),map.get("label"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok(9);

    }


}
