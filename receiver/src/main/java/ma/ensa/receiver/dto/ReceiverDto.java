package ma.ensa.receiver.dto;

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

    private String name;

    private String phoneNumber;

    private String userId;

    private boolean verified;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
