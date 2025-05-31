package ma.ensa.gatway.dto;


import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
}
