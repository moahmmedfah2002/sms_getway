package ma.ensa.receiver.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CsvReceiverDto {
    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "PhoneNumber")
    private String phoneNumber;

    private String userId;

    public void sanitize() {
        if (name != null) name = name.trim();
        if (phoneNumber != null) phoneNumber = phoneNumber.trim();
        if (name != null) name = name.trim();
    }
}
