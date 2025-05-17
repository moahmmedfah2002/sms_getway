package ma.ensa.gatway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.gatway.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse  {
    private String token;
    private User user;
    private Boolean valid;
}

