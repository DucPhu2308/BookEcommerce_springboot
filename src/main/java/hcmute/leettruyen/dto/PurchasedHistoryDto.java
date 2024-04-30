package hcmute.leettruyen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedHistoryDto {
    private int coin;
    private Integer chapter;
    private Integer user;
}
