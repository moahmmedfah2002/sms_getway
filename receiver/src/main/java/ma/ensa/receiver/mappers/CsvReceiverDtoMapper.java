package ma.ensa.receiver.mappers;

import ma.ensa.receiver.dto.CsvReceiverDto;
import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.entities.Receiver;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CsvReceiverDtoMapper {
    public static CsvReceiverDto toDto(Receiver receiver) {
        if (receiver == null) {
            return null;
        }
        CsvReceiverDto dto = new CsvReceiverDto();
        BeanUtils.copyProperties(receiver, dto);
        return dto;
    }

    public static Receiver toEntity(CsvReceiverDto dto) {
        if (dto == null) {
            return null;
        }
        Receiver receiver = new Receiver();
        BeanUtils.copyProperties(dto, receiver);
        return receiver;
    }
}
