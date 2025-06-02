package ma.ensa.schedule.openFeaign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "sms", url = "gateway:7001/sendmsg")
@Service
public interface SmsFeign {
    @PostMapping("/send")
    int sendSms(@RequestBody Map<String, String> params);
}
