package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserResponse {
    private int id;
    private String displayName;
    private String avatar;
    private String introduction;
    private List<BookResponse> own;
    private Integer user_follow;
    private boolean isFollow;
}
