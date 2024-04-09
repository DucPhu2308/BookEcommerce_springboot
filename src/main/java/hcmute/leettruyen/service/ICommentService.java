package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.CommentDto;
import hcmute.leettruyen.response.CommentResponse;

import java.util.List;

public interface ICommentService {
    CommentResponse createComment(CommentDto commentDto) throws Exception;
    CommentResponse updateComment(Integer id, CommentDto commentDto) throws Exception;
    void deleteComment(Integer id);
    List<CommentResponse> getCommentByChapter(Integer id) throws Exception;
}
