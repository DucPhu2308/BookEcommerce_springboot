package hcmute.leettruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTransaction {
    @Id
    @GeneratedValue
    private Integer id;
    private int coin;
    private Float amount;
    private LocalDateTime dateTime;
    private Boolean isDeposit;
    @ManyToOne
    @JoinColumn(
            name = "payment_method_id"
    )
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}
