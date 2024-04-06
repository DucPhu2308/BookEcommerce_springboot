package hcmute.leettruyen.dto;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.Comment;
import hcmute.leettruyen.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private String content;
    private Integer parent;
    private Integer chapter;
    private Integer user;
}
