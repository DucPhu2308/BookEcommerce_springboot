package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.UpdateInfoDto;
import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.ParagraphResponse;
import hcmute.leettruyen.response.UserResponse;
import hcmute.leettruyen.response.ViewUserResponse;

import java.net.URISyntaxException;
import java.util.List;

public interface IUserService {

    UserResponse updateUserById(UpdateInfoDto userDto , Integer userId) throws URISyntaxException;
    List<UserResponse> getAllUser();
    void createUser(UserDto userDto);
    UserResponse updateUserInfo(UpdateInfoDto userDto) throws URISyntaxException;
    String login(String email, String passWord) throws Exception;
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    void followBook(Integer bookId);
    List<BookResponse> getFollowBook();
    void markParagraph(Integer paragraphId);
    List<ParagraphResponse> getMarkParagraph();
    void followUser(Integer userId);
    List<UserResponse> getFollowingUser();
    List<UserResponse> getFollowedUser();
    void changePassword(String password);
    Integer buyChapter(Integer chapterId) throws Exception;
    UserResponse confirmToken(String token, String email) throws Exception;
    ViewUserResponse viewUser(Integer userId);
}
