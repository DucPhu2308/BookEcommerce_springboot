package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.ParagraphDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IParagraphService;
import hcmute.leettruyen.service.implement.FirebaseStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/paragraph")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ParagraphController {
    private final IParagraphService paragraphService;
    private final FirebaseStorageService firebaseStorageService;
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
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            paragraphService.getParagraphByChapter(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseObject> uploadImages(
            @ModelAttribute("files") MultipartFile file ) throws IOException {
        if (file.getSize() > 10 * 1024 * 1024) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body(new ResponseObject("failed", "your image too large", ""));
        }
        if(file.getContentType()== null || !file.getContentType().startsWith("image/")){
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(new ResponseObject("failed","file must be an image",""));
        }
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID()+"_"+filename;
        InputStream inputStream = file.getInputStream();
        firebaseStorageService.uploadFile("Paragraph",inputStream,uniqueFileName);
        inputStream.close();
        return ResponseEntity.ok(
                new ResponseObject("Success","upload image success",uniqueFileName)
        );
    }
}
