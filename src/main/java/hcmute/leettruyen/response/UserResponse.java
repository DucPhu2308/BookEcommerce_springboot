package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String email;
    private String displayName;
    private int coin;
    private String avatar;
    private String introduction;
}
