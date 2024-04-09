package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.RatingDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IRatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RatingController {
    private final IRatingService ratingService;
    @GetMapping("/book/{id}")
    public ResponseEntity<ResponseObject> ratingByBook(
        @PathVariable Integer id){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            ratingService.getRatingByBook(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("")
    public ResponseEntity<ResponseObject> createRating(
            @Valid @RequestBody RatingDto ratingDto,
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
                            ratingService.createRating(ratingDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateRating(
            @PathVariable Integer id,
            @Valid @RequestBody RatingDto ratingDto,
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
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            ratingService.updateRating(id,ratingDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteRating(
            @PathVariable Integer id
    ){
        try {
            ratingService.deleteRating(id);
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
