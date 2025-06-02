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

    @Scheduled(fixedRate = 5000)
    public void processScheduledSms() {
        System.out.println("processing");
        LocalDateTime now = LocalDateTime.now();
        List<ScheduledSms> pendingSms = scheduledSmsRepository.findByStatusAndScheduledTimeBefore("PENDING", now);
        System.out.println(pendingSms.size());

        for (ScheduledSms sms : pendingSms) {
            try {

                System.out.println("schedule is running");
                smsService.sendSms(sms.getRecipientPhoneNumber(), sms.getMessage(), "label");




                sms.setStatus("SENT");
                scheduledSmsRepository.save(sms);
            } catch (Exception e) {
                System.out.println(e.getMessage());

                sms.setStatus("FAILED");
                scheduledSmsRepository.save(sms);
            }
        }
    }
}
