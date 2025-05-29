package ma.ensa.receiver.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverDto {
    private Long id;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "PhoneNumber")
    private String phoneNumber;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
