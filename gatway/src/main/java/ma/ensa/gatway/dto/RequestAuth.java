package ma.ensa.gatway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.gatway.entity.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestAuth {
    private String email;
    private String username;
    private String password;
    private Role Role= ma.ensa.gatway.entity.Role.USER;
    private String firstName;
    private String lastName;
    private String phone;


}
