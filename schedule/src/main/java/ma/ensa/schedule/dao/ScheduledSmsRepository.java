package ma.ensa.schedule.dao;

import ma.ensa.schedule.entity.ScheduledSms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledSmsRepository extends JpaRepository<ScheduledSms, Long> {
    List<ScheduledSms> findByStatusAndScheduledTime(String status, LocalDateTime scheduledTime);
    List<ScheduledSms> findByUserIdAndStatus(Long userId, String status);


}
