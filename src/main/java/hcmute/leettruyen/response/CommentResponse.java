package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String content;
//    private CommentResponse parent;
    private List<CommentResponse> children;
    private UserResponse user;
}
