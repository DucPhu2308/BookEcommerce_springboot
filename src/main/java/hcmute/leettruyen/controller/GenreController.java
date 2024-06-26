package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.GenreDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IGenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class GenreController {
    private final IGenreService genreService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> createGenre(
            @Valid @RequestBody GenreDto genreDto,
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
                            genreService.createGenre(genreDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> findAllGenre(){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            genreService.getAllGenre()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateGenre(
            @PathVariable Integer id,
            @Valid @RequestBody GenreDto genreDto,
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
                            genreService.updateGenre(id,genreDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/{id}/books")
    public ResponseEntity<ResponseObject> findBookByGenre(
            @PathVariable Integer id
    ){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            genreService.booksByGenre(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteGenre(
            @PathVariable Integer id
    ){
        try {
            genreService.deleteGenre(id);
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
