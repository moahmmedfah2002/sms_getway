package ma.ensa.sendmsg.dto;

import lombok.Data;

@Data
public class RequestSend {
    private String message;
    private String phoneNumber;
}
