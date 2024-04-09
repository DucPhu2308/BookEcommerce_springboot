package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.CommentDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.ICommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> createComment(
            @Valid @RequestBody CommentDto commentDto,
            BindingResult result
            ){
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    new ResponseObject("failed", errorMessages.toString(),""));
        }
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            commentService.createComment(commentDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/chapter/{id}")
    public ResponseEntity<ResponseObject> getCommentByChapter(
            @PathVariable Integer id
    ){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            commentService.getCommentByChapter(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateComment(
            @PathVariable Integer id,
            @Valid @RequestBody CommentDto commentDto,
            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    new ResponseObject("failed", errorMessages.toString(),""));
        }
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            commentService.updateComment(id,commentDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteComment(
            @PathVariable Integer id
    ){
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            ""));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
