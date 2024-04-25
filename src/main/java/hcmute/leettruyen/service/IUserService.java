package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.Paragraph;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.ParagraphResponse;

import java.util.List;

public interface IUserService {
    User createUser(UserDto userDto);
    String login(String email, String passWord) throws Exception;
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    void followBook(Integer bookId);
    List<BookResponse> getFollowBook();
    void markParagraph(Integer paragraphId);
    List<ParagraphResponse> getMarkParagraph();
    void followUser(Integer userId);
}
