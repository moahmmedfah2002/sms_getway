package ma.ensa.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.auth.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestAuth {
    private String username;
    private String password;
    private Role Role= ma.ensa.auth.entity.Role.USER;
    private String firstName;
    private String lastName;
    private String phone;


}
