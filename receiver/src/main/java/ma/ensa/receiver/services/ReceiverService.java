package ma.ensa.receiver.services;

import ma.ensa.receiver.dto.CsvImportResponseDto;
import ma.ensa.receiver.dto.CsvReceiverDto;
import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.execptions.PhoneNumberExistsException;
import ma.ensa.receiver.execptions.ReceiverNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReceiverService {
    Page<Receiver> getReceivers(String query, String userId, Pageable pageable);

    Receiver getReceiverById(Long id) throws ReceiverNotFoundException;

    Receiver createReceiver(ReceiverDto receiverDto) throws PhoneNumberExistsException;

    Receiver updateReceiver(Long id, ReceiverDto receiverDto) throws PhoneNumberExistsException, ReceiverNotFoundException;

    void deleteReceiver(Long id) throws ReceiverNotFoundException;

    int deleteReceivers(List<Long> ids);

    CsvImportResponseDto importReceivers(List<CsvReceiverDto> receivers);
}
