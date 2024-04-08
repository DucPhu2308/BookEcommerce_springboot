package hcmute.leettruyen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionDto {
    private int coin;
    private Float amount;
    private Integer paymentMethod;
    private Integer user;
}
