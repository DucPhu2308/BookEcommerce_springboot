package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.UpdateInfoDto;
import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.ParagraphResponse;
import hcmute.leettruyen.response.UserResponse;

import java.util.List;

public interface IUserService {
    User createUser(UserDto userDto);
    UserResponse updateUserInfo(UpdateInfoDto userDto);
    String login(String email, String passWord) throws Exception;
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    void followBook(Integer bookId);
    List<BookResponse> getFollowBook();
    void markParagraph(Integer paragraphId);
    List<ParagraphResponse> getMarkParagraph();
    void followUser(Integer userId);
    void changePassword(String password);
}
