package hcmute.leettruyen.controller;

import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
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
}
