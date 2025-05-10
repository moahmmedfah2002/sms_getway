package ma.ensa.receiver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchDeleteDto {
    private List<Long> ids;
}

