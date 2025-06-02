package ma.ensa.schedule.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class ScheduleRequest {

    private String phoneNumber;
    private String message;
    private LocalDateTime scheduledTime;
    private Long userId;
    private String label;
}

