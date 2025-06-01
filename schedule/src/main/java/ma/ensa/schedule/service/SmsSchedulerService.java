package ma.ensa.schedule.service;
import ma.ensa.schedule.dao.ScheduledSmsRepository;
import ma.ensa.schedule.entity.ScheduledSms;
import ma.ensa.schedule.openFeaign.SmsFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SmsSchedulerService {

    @Autowired
    private ScheduledSmsRepository scheduledSmsRepository;



    private SmsService smsService;

    public SmsSchedulerService(SmsService smsService) {
        this.smsService = smsService;
    }

    @Scheduled(fixedRate = 60000)
    public void processScheduledSms() {
        LocalDateTime now = LocalDateTime.now();
        List<ScheduledSms> pendingSms = scheduledSmsRepository.findByStatusAndScheduledTime("PENDING", now);

        for (ScheduledSms sms : pendingSms) {
            try {

                smsService.sendSms(sms.getRecipientPhoneNumber(), sms.getMessage(), "label");




                sms.setStatus("SENT");
                scheduledSmsRepository.save(sms);
            } catch (Exception e) {

                sms.setStatus("FAILED");
                scheduledSmsRepository.save(sms);
            }
        }
    }
}
