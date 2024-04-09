package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    @GetMapping("")
    public ResponseEntity<ResponseObject> getBook(
            @RequestParam("page") int page
    ){
        PageRequest pageRequest = PageRequest.of(
                page, 10, Sort.by("createdAt").descending());
        Page<BookResponse> bookResponsePage = bookService.getAllBook(pageRequest);
        int totalPage = bookResponsePage.getTotalPages();
        List<BookResponse> bookResponses = bookResponsePage.getContent();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("totalPage", totalPage);
        responseData.put("books", bookResponses);
        return ResponseEntity.ok(
                new ResponseObject("ok",
                        "",responseData));
    }
    @PostMapping("")
    public ResponseEntity<ResponseObject> createBook(
            @Valid @RequestBody BookDto bookDto,
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
                            bookService.createBook(bookDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getBookById(
            @PathVariable Integer id
    ){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            bookService.getBookById(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBook(
            @PathVariable Integer id,
            @Valid @RequestBody BookDto bookDto,
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
                            bookService.updateBook(id,bookDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
