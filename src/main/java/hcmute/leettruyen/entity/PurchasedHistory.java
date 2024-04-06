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
public class PurchasedHistory {
    @Id
    @GeneratedValue
    private Integer id;
    private int coin;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
