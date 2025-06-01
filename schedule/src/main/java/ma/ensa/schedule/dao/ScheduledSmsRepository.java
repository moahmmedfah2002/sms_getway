package ma.ensa.schedule.dao;

import ma.ensa.schedule.entity.ScheduledSms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledSmsRepository extends JpaRepository<ScheduledSms, Long> {

    List<ScheduledSms> findByStatusAndScheduledTimeBefore(String status, LocalDateTime time);
    List<ScheduledSms> findByUserIdAndStatus(Long userId, String status);

    @Query("select s from ScheduledSms s")
    List<ScheduledSms> fetchAll();


}
