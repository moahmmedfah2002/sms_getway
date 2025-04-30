package ma.ensa.receiver.services.impl;

import lombok.AllArgsConstructor;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.repository.ReceiverRepository;
import ma.ensa.receiver.services.ReceiverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {
    private final ReceiverRepository receiverRepository;

    @Override
    public Page<Receiver> getReceivers(String query, String userId, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            if (userId != null) {
                return receiverRepository.findByUserIdAndQuery(userId, query, pageable);
            } else {
                return receiverRepository.findByQuery(query, pageable);
            }
        } else {
            if (userId != null) {
                return receiverRepository.findByUserId(userId, pageable);
            } else {
                return receiverRepository.findAll(pageable);
            }
        }
    }
}
