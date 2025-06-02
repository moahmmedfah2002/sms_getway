package ma.ensa.sendmsg.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

@Table(name="sms")
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String phoneNumber;
    private String label;
    private String message;
    private String toType;

    private LocalDateTime sendTime;
    private String status;
}
