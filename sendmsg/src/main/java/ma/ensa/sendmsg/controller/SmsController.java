package ma.ensa.sendmsg.controller;

import ma.ensa.sendmsg.dao.SmsRepository;
import ma.ensa.sendmsg.entity.Sms;
import ma.ensa.sendmsg.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class SmsController {
    private final SmsService smsService;
    private final SmsRepository smsRepository;

    public SmsController(
            SmsService smsService,
            SmsRepository smsRepository
    ) {
        this.smsService = smsService;
        this.smsRepository = smsRepository;

    }
    @PostMapping("/send")
    //@RequestParam String phoneNumber,@RequestParam String message
    public ResponseEntity<Integer> send(@RequestBody Map<String, String> request) throws IOException {
//        String phoneNumber = request.getOrDefault("phoneNumber", "");
//        return ResponseEntity.ok(smsService.sendSms(phoneNumber,message));
//        System.out.println(phoneNumber);
        String phoneNumber = (String) request.get("phoneNumber");
        String message = (String) request.get("message");
        String label = (String) request.get("label");
        System.out.println(phoneNumber);
        Sms sms = new Sms();
        sms.setPhoneNumber(phoneNumber);
        sms.setMessage(message);
        sms.setLabel(label);
        sms.setSendTime(LocalDateTime.now());
        sms.setStatus("Sent");
        sms.setToType("receiver");
        smsRepository.save(sms);

        var code = smsService.sendSms(phoneNumber, message, label);

        System.out.println(code);
        return ResponseEntity.ok(0);


    }

    @PostMapping("/send-group")

    public ResponseEntity<Integer> sendToGroup(@RequestBody List<Map<String, String> > requests) throws IOException {

        System.out.println("reached here");
        requests.forEach(receiver -> {
            try {
                smsService.sendSms(receiver.get("phoneNumber"), receiver.get("message"), receiver.get("label"));

                Sms sms = new Sms();
                sms.setPhoneNumber(receiver.get("phoneNumber"));
                sms.setMessage(receiver.get("message"));
                sms.setLabel(receiver.get("label"));
                sms.setSendTime(LocalDateTime.now());
                sms.setStatus("Sent");
                sms.setToType("group");

                smsRepository.save(sms);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        return ResponseEntity.ok(0);


    }

    @GetMapping("/history")
    public ResponseEntity<List<Sms>> history() throws IOException {

        List<Sms> history = smsRepository.findAll();
        return ResponseEntity.ok(history);

    }


}
