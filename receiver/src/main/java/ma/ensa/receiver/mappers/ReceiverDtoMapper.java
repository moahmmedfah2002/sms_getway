package ma.ensa.receiver.mappers;

import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.entities.Receiver;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReceiverDtoMapper {
    public ReceiverDto toDto(Receiver receiver) {
        if (receiver == null) {
            return null;
        }
        ReceiverDto dto = new ReceiverDto();
        BeanUtils.copyProperties(receiver, dto);
        return dto;
    }

    public Receiver toEntity(ReceiverDto dto) {
        if (dto == null) {
            return null;
        }
        Receiver receiver = new Receiver();
        BeanUtils.copyProperties(dto, receiver);
        return receiver;
    }
}
