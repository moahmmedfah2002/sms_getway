package ma.ensa.receiver.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
}
