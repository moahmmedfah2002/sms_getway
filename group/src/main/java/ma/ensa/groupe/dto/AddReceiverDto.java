package ma.ensa.groupe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddReceiverDto {
    @NotNull(message = "Receiver ID is required")
    private Long receiverId;
}
