package ma.ensa.receiver.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.execptions.PhoneNumberExistsException;
import ma.ensa.receiver.execptions.ReceiverNotFoundException;
import ma.ensa.receiver.mappers.ReceiverDtoMapper;
import ma.ensa.receiver.repository.ReceiverRepository;
import ma.ensa.receiver.services.ReceiverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {
private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{1,15}$");
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


    @Override
    public Receiver getReceiverById(Long id) throws ReceiverNotFoundException {
        return receiverRepository.findById(id)
                .orElseThrow(() -> new ReceiverNotFoundException("Receiver not found with id: " + id));
    }

    @Override
    @Transactional
    public Receiver createReceiver(ReceiverDto receiverDto) throws PhoneNumberExistsException {
        System.out.println("Creating receiver: " + receiverDto);
        validateReceiver(receiverDto, null);
        System.out.println("Receiver is valid");
        Receiver receiver = ReceiverDtoMapper.toEntity(receiverDto);
        System.out.println("Receiver entity: " + receiver);
        receiverRepository.save(receiver);
        return receiver;

    }

    @Override
    @Transactional
    public Receiver updateReceiver(Long id, ReceiverDto receiverDto) throws PhoneNumberExistsException, ReceiverNotFoundException {
        Receiver existingReceiver = getReceiverById(id);

        validateReceiver(receiverDto, id);

        // If phone number changed, reset verification status
        boolean phoneChanged = !existingReceiver.getPhoneNumber().equals(receiverDto.getPhoneNumber());

        existingReceiver.setName(receiverDto.getName());
        existingReceiver.setPhoneNumber(receiverDto.getPhoneNumber());
        existingReceiver.setUserId(receiverDto.getUserId());

        if (phoneChanged) {
            existingReceiver.setVerified(false);
        }

        return receiverRepository.save(existingReceiver);
    }

    @Override
    @Transactional
    public void deleteReceiver(Long id) throws ReceiverNotFoundException {
        if (!receiverRepository.existsById(id)) {
            throw new ReceiverNotFoundException("Receiver not found with id: " + id);
        }
        receiverRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteReceivers(List<Long> ids) {
        List<Receiver> receiversToDelete = receiverRepository.findByIdIn(ids);
        receiverRepository.deleteAll(receiversToDelete);
        return receiversToDelete.size();
    }


    private void validateReceiver(ReceiverDto receiverDto, Long id) throws PhoneNumberExistsException {
        if (receiverDto.getName() == null || receiverDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (receiverDto.getPhoneNumber() == null || receiverDto.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        if (!PHONE_PATTERN.matcher(receiverDto.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("Phone number must be in international format (e.g., +1234567890)");
        }

        if (receiverDto.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        // Check for duplicate phone number
        boolean phoneExists;
        if (id != null) {
            phoneExists = receiverRepository.existsByPhoneNumberAndIdNot(receiverDto.getPhoneNumber(), id);
        } else {
            phoneExists = receiverRepository.existsByPhoneNumber(receiverDto.getPhoneNumber());
        }

        if (phoneExists) {
            throw new PhoneNumberExistsException("A receiver with this phone number already exists");
        }
    }
}
