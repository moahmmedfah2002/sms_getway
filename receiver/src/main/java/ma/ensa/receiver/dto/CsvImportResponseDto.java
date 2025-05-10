package ma.ensa.receiver.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class CsvImportResponseDto {
    private List<CsvReceiverDto> valid;
    private List<Map<String, Object>> invalid;
    private int totalValid;
    private int totalInvalid;
}
