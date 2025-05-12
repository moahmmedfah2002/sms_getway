package ma.ensa.groupe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String userId;
    private boolean verified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
