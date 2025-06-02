package ma.ensa.schedule.controller;


import ma.ensa.schedule.dao.ScheduledSmsRepository;
import ma.ensa.schedule.dto.ScheduleRequest;
import ma.ensa.schedule.entity.ScheduledSms;
import ma.ensa.schedule.openFeaign.SmsFeign;
import ma.ensa.schedule.service.SmsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController

public class SmsScheduler {


    private SmsService smsService;
    private ScheduledSmsRepository scheduledSmsRepository;


    public SmsScheduler(
            SmsService smsService,
            ScheduledSmsRepository scheduledSmsRepository
    ) {
        this.smsService = smsService;
        this.scheduledSmsRepository = scheduledSmsRepository;
    }

    @PostMapping
    public ResponseEntity<Integer> schedule(@RequestBody ScheduleRequest request) {

        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", "23423423432");
        map.put("label", "23423423432");
        map.put("message", "23423423432");

        ScheduledSms scheduledSms = new ScheduledSms();
        scheduledSms.setRecipientPhoneNumber(request.getPhoneNumber());
        scheduledSms.setLabel(request.getLabel());
        scheduledSms.setMessage(request.getMessage());
        scheduledSms.setUserId(request.getUserId());
        scheduledSms.setStatus("PENDING");
        scheduledSms.setScheduledTime(request.getScheduledTime());



        try {
            smsService.sendSms(map.get("phoneNumber"),map.get("message"),map.get("label"));


        } catch (IOException e) {
            throw new RuntimeException(e);
//            return ResponseEntity.ok(0);
        }

        System.out.println("passed here");
        ScheduledSms savedSms = scheduledSmsRepository.save(scheduledSms);

        System.out.println("passed here too");



        return ResponseEntity.ok(1);

    }

    @GetMapping
    public ResponseEntity<List<ScheduledSms>> getAll() {

        List<ScheduledSms> scheduledSms = scheduledSmsRepository.findAll();
        return ResponseEntity.ok(scheduledSms);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable long id){

        System.out.println("deleting "+id);

        scheduledSmsRepository.deleteById(id);
        return ResponseEntity.ok(1);

    }


}
