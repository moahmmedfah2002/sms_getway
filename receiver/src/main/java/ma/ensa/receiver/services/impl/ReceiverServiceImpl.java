package ma.ensa.receiver.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.ensa.receiver.dto.CsvImportResponseDto;
import ma.ensa.receiver.dto.CsvReceiverDto;
import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.execptions.PhoneNumberExistsException;
import ma.ensa.receiver.execptions.ReceiverNotFoundException;
import ma.ensa.receiver.mappers.CsvReceiverDtoMapper;
import ma.ensa.receiver.mappers.ReceiverDtoMapper;
import ma.ensa.receiver.repository.ReceiverRepository;
import ma.ensa.receiver.services.ReceiverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {
    private static final Pattern E164_PATTERN = Pattern.compile("^\\+?[0-9]{1,15}$");
    private final ReceiverRepository receiverRepository;

    @Override
    public Page<Receiver> getReceivers(String query, Long userId, Pageable pageable) {
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

        existingReceiver.setName(receiverDto.getName());
        existingReceiver.setPhoneNumber(receiverDto.getPhoneNumber());
        existingReceiver.setUserId(receiverDto.getUserId());


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


    @Override
    public CsvImportResponseDto importReceivers(List<CsvReceiverDto> receivers) {
        CsvImportResponseDto response = new CsvImportResponseDto();
        List<CsvReceiverDto> validReceivers = new ArrayList<>();
        List<Map<String, Object>> invalidReceivers = new ArrayList<>();

        for (int i = 0; i < receivers.size(); i++) {
            CsvReceiverDto receiver = receivers.get(i);
            List<String> errors = validateCsvReceiver(receiver);

            if (errors.isEmpty()) {
                validReceivers.add(receiver);

            } else {
                Map<String, Object> invalidReceiver = new HashMap<>();
                invalidReceiver.put("row", i + 2); // Adding 2 because of zero-indexing and header row
                invalidReceiver.put("data", receiver);
                invalidReceiver.put("errors", errors);
                invalidReceivers.add(invalidReceiver);
            }
        }

        receiverRepository.saveAll(validReceivers.stream().map(CsvReceiverDtoMapper::toEntity).toList());

        response.setValid(validReceivers);
        response.setInvalid(invalidReceivers);
        response.setTotalValid(validReceivers.size());
        response.setTotalInvalid(invalidReceivers.size());
        return response;
    }


    private void validateReceiver(ReceiverDto receiverDto, Long id) throws PhoneNumberExistsException {
        if (receiverDto.getName() == null || receiverDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (receiverDto.getPhoneNumber() == null || receiverDto.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        if (!E164_PATTERN.matcher(receiverDto.getPhoneNumber()).matches()) {
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

    private List<String> validateCsvReceiver(CsvReceiverDto receiverDto) {
        System.out.println("validate csv receiver: -----");
        System.out.println(receiverDto.getPhoneNumber());
        List<String> errors = new ArrayList<>();

        if (receiverDto.getName() == null || receiverDto.getName().trim().isEmpty()) {
            errors.add("Name is required");
        }

        if (receiverDto.getPhoneNumber() == null || receiverDto.getPhoneNumber().trim().isEmpty()) {
            errors.add("Phone number is required");
        } else if (!E164_PATTERN.matcher(receiverDto.getPhoneNumber()).matches()) {
            System.out.println("sorry does not match");
            errors.add("Phone number must be in international format (e.g., +1234567890)");
        } else if (receiverRepository.existsByPhoneNumber(receiverDto.getPhoneNumber())) {
            errors.add("A receiver with this phone number already exists");
        }
        System.out.println("-----");

        if (receiverDto.getUserId() == null) {
            errors.add("User ID is required");
        }

        return errors;
    }

}
