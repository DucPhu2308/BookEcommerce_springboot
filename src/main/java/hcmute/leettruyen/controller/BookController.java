package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.ResponseObject;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
//    @PostMapping("")
//    public ResponseEntity<ResponseObject> createBook(
//            @Valid @RequestBody BookDto bookDto,
//            BindingResult result){
//        if(result.hasErrors()){
//            List<String> errorMessages = result.getFieldErrors()
//                    .stream()
//                    .map(FieldError::getDefaultMessage)
//                    .toList();
//            return ResponseEntity.badRequest().body(
//                    new ResponseObject("failed", errorMessages.toString(),""));
//        }
//        try {
//
//        }catch (Exception e){
//
//
//        }
//    }
}
