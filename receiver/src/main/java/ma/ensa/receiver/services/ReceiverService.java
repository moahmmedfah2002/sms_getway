package ma.ensa.receiver.services;

import ma.ensa.receiver.entities.Receiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceiverService {
    Page<Receiver> getReceivers(String query, String userId, Pageable pageable);
}
