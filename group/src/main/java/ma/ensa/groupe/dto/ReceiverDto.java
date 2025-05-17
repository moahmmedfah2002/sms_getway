package ma.ensa.groupe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String userId;
    private String createdAt;
    private String updatedAt;
}
