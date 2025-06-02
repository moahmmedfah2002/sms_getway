package ma.ensa.schedule.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_sms")
@Data
public class ScheduledSms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recipientPhoneNumber;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime scheduledTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long userId;


}
