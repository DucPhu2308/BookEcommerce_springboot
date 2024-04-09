package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.CommentDto;
import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.Comment;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.ChapterRepository;
import hcmute.leettruyen.repository.CommentRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.response.CommentResponse;
import hcmute.leettruyen.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;
    private final ModelMapper modelMapper;
    @Override
    public CommentResponse createComment(CommentDto commentDto) throws Exception {
        Comment comment = new Comment();
        if(commentDto.getParent()!=null){
            Comment foundComment = commentRepository.findById(commentDto.getParent())
                    .orElseThrow(()-> new Exception("Cannot find parent"));
            comment.setParent(foundComment);
        }
        User foundUser = userRepository.findById(commentDto.getUser())
                .orElseThrow(()-> new Exception("Cannot find user"));
        Chapter foundChapter = chapterRepository.findById(commentDto.getChapter())
                .orElseThrow(()-> new Exception("Cannot find user"));
        comment.setUser(foundUser);
        comment.setChapter(foundChapter);
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(Integer id, CommentDto commentDto) throws Exception {
        Comment foundComment = commentRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find comment"));
        foundComment.setContent(commentDto.getContent());
        commentRepository.save(foundComment);
        return modelMapper.map(foundComment,CommentResponse.class);
    }

    @Override
    public void deleteComment(Integer id) {

    }

    @Override
    public List<CommentResponse> getCommentByChapter(Integer id) throws Exception {
        Chapter foundChapter = chapterRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find comment"));
        List<Comment> comments = commentRepository.findAllByChapter(foundChapter);
        List<Comment> rootComments = comments.stream()
                .filter(comment -> comment.getParent() == null)
                .toList();
        return rootComments.stream()
                .map(comment -> modelMapper.map(comment,CommentResponse.class))
                .collect(Collectors.toList());
    }
}
