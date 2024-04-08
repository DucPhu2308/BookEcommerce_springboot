package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Comment;
import hcmute.leettruyen.entity.Paragraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterResponse {
    private String title;
    private int price;
    private int index;
    private Book book;
    private List<Comment> comments;
    private List<Paragraph> paragraphs;
}
