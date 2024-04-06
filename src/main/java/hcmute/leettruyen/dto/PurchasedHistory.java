package hcmute.leettruyen.dto;

import hcmute.leettruyen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedHistory {
    private int coin;
    private Integer chapter;
    private Integer user;
}
