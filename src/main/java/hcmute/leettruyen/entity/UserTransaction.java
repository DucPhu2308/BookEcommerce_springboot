package hcmute.leettruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTransaction {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer coin;
    private Float amount;
    private Timestamp dateTime;
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
