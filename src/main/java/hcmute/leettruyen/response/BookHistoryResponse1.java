package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookHistoryResponse1 {
    private BookResponse bookRead;
    private LocalDateTime viewedAt;
}
