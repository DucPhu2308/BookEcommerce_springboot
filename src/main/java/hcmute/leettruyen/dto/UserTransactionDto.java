package hcmute.leettruyen.dto;

import hcmute.leettruyen.entity.PaymentMethod;
import hcmute.leettruyen.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionDto {
    private int coin;
    private Float amount;
    private Integer paymentMethod;
    private Integer user;

}
