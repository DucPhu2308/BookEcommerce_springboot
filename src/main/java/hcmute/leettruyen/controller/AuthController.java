package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.LoginDto;
import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.response.UserResponse;
import hcmute.leettruyen.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final ModelMapper modelMapper;
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @Valid @RequestBody UserDto userDto,
            BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    new ResponseObject("failed", errorMessages.toString(),""));
        }
        try {
            userService.createUser(userDto);
            return ResponseEntity.ok(
                    new ResponseObject("ok","","")
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(
            @Valid @RequestBody LoginDto loginDto,
            BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    new ResponseObject("failed", errorMessages.toString(),""));
        }
        try {
            String token = userService.login(loginDto.getEmail(), loginDto.getPassword());
            User user = userService.findByEmail(loginDto.getEmail());
            return getResponseObjectResponseEntity(token, user);
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ResponseObject("fail",e.getMessage(),"")
            );
        }
    }
    @PostMapping("/confirm")
    public ResponseEntity<ResponseObject> confirmToken(
            @RequestParam("token") String token,
            @RequestParam("email") String email
    ){
        try {
            UserResponse user = userService.confirmToken(token, email);
            return ResponseEntity.ok(
                    new ResponseObject("ok","",user)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("fail",e.getMessage(),"")
            );
        }
    }

    private ResponseEntity<ResponseObject> getResponseObjectResponseEntity(String token, User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("token", token);
        responseData.put("user", userResponse);
        responseData.put("roles", user.getAllRoles());
        return ResponseEntity.ok(
                new ResponseObject("ok","",responseData)
        );
    }
}
