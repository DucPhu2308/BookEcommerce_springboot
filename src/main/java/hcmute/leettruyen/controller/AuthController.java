package hcmute.leettruyen.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                User user = userService.createUser(userDto);
                String token = userService.login(userDto.getEmail(), userDto.getPassword());
                UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("user", userResponse);
            responseData.put("roles", user.getAllRoles());
            return ResponseEntity.ok(
                    new ResponseObject("ok","",responseData)
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(
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
            String token = userService.login(userDto.getEmail(), userDto.getPassword());
            User user = userService.findByEmail(userDto.getEmail());
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("user", userResponse);
            responseData.put("roles", user.getAllRoles());
            return ResponseEntity.ok(
                    new ResponseObject("ok","",responseData)
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    new ResponseObject("fail",e.getMessage(),"")
            );
        }
    }
}
