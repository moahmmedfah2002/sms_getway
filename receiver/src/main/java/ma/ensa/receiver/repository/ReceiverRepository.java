package ma.ensa.receiver.repository;

import ma.ensa.receiver.entities.Receiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
    Page<Receiver> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT r FROM Receiver r WHERE r.userId=:userId AND (r.name LIKE %:query% OR r.phoneNumber LIKE %:query%)")
    Page<Receiver> findByUserIdAndQuery(@Param("userId") Long userId, @Param("query") String query, Pageable pageable);

    @Query("SELECT r FROM Receiver r WHERE r.name LIKE %:query% OR r.phoneNumber LIKE %:query%")
    Page<Receiver> findByQuery(@Param("query") String query, Pageable pageable);

    List<Receiver> findByIdIn(List<Long> ids);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
