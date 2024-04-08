package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.Comment;
import hcmute.leettruyen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String content;
    private Comment parent;
    private List<Comment> children;
    private Chapter chapter;
    private User user;
}
