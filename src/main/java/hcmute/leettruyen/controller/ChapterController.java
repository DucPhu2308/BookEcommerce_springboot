package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.ChapterDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.response.ChapterResponse;
import hcmute.leettruyen.service.IChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chapter")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ChapterController {
    private final IChapterService chapterService;
    @GetMapping("/book/{id}")
    public ResponseEntity<ResponseObject> getChapterByBook(
            @PathVariable Integer id
    ){

        try {
            List<ChapterResponse> chapterResponses = chapterService.chapterByBook(id).stream()
                    .sorted(Comparator.comparingInt(ChapterResponse::getIndex))
                    .toList();
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            chapterResponses));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getChapterById(
            @PathVariable Integer id
    ){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            chapterService.getChapterById(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteChapter(
            @PathVariable Integer id
    ){
        try {
            chapterService.deleteChapter(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",""));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping("")
    public ResponseEntity<ResponseObject> createChapter(
            @Valid @RequestBody ChapterDto chapterDto,
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
                            chapterService.createChapter(chapterDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateChapter(
            @PathVariable Integer id,
            @Valid @RequestBody ChapterDto chapterDto,
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
                            chapterService.updateChapter(id,chapterDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
