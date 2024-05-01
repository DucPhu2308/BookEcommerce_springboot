package hcmute.leettruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookHistory {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User userRead;
    @ManyToOne
    @JoinColumn(
            name = "book_id"
    )
    private Book bookRead;
    private LocalDateTime viewedAt;
}
