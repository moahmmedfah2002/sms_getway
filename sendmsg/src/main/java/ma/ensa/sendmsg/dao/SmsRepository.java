package ma.ensa.sendmsg.dao;

import ma.ensa.sendmsg.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SmsRepository extends JpaRepository<Sms, Long> {

    @Query("select s from Sms s")
    public List<Sms> fetchAll();
}
