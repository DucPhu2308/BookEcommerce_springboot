package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.ParagraphDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IChapterService;
import hcmute.leettruyen.service.IHistoryService;
import hcmute.leettruyen.service.IParagraphService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paragraph")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ParagraphController {
    private final IParagraphService paragraphService;
    private final IHistoryService historyService;
    private final IChapterService chapterService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> createParagraph(
            @Valid @RequestBody ParagraphDto paragraphDto,
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
                            paragraphService.createParagraph(paragraphDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateParagraph(
            @PathVariable Integer id,
            @Valid @RequestBody ParagraphDto paragraphDto,
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
                            paragraphService.updateParagraph(id,paragraphDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteParagraph(
            @PathVariable Integer id
    ){
        try {
            paragraphService.deleteParagraph(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            ""));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @GetMapping("/chapter/{id}")
    public ResponseEntity<ResponseObject> getParagraphByChapter(
            @PathVariable Integer id
    ){
        try {
            historyService.createBookHistory(id);
            chapterService.increaseView(id);
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            paragraphService.getParagraphByChapter(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
