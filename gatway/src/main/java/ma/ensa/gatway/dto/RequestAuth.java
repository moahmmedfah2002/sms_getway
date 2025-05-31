package ma.ensa.gatway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.gatway.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestAuth {
    private String email;
    private String username;
    private String password;
    private Role role= Role.USER;
    private String firstName;
    private String lastName;
    private String phone;


}
