package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.UpdateInfoDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping("/follow/{id}/book")
    public ResponseEntity<ResponseObject> followBook(
            @PathVariable Integer id
    ){
        try {
            userService.followBook(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",""));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("/follow/{id}/user")
    public ResponseEntity<ResponseObject> followUser(
            @PathVariable Integer id
    ){
        try {
            userService.followUser(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",""));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("/add-bookmark/{id}")
    public ResponseEntity<ResponseObject> markParagraph(
            @PathVariable Integer id
    ){
        try {
            userService.markParagraph(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",""));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/follow/book")
    public ResponseEntity<ResponseObject> getFollowBook(){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",userService.getFollowBook()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/book-mark")
    public ResponseEntity<ResponseObject> getMarkParagraph(){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",userService.getMarkParagraph()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/following")
    public ResponseEntity<ResponseObject> getFollowingUser(){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",userService.getFollowingUser()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/followed")
    public ResponseEntity<ResponseObject> getFollowedUser(){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",userService.getFollowedUser()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseObject> update(
            @Valid @RequestBody UpdateInfoDto updateInfoDto
    ){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",userService.updateUserInfo(updateInfoDto)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/change-password")
    public ResponseEntity<ResponseObject> changePassword(
            @RequestParam String password
    ){
        try {
            userService.changePassword(password);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",""));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("/buy/{chapterId}")
    public ResponseEntity<ResponseObject> buyChapter(
            @PathVariable Integer chapterId
    ){
        try {
            userService.buyChapter(chapterId);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",""));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
