package hcmute.leettruyen.controller;

import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.implement.FirebaseStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/upload")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class UploadImageController {
    private final FirebaseStorageService firebaseStorageService;
    @PostMapping(value = "/{type}"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseObject> uploadImages(
            @PathVariable Integer type,
            @ModelAttribute("file") MultipartFile file ) throws IOException {
        if (file.getSize() > 10 * 1024 * 1024) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body(new ResponseObject("failed", "your image too large", ""));
        }
        if(file.getContentType()== null || !file.getContentType().startsWith("image/")){
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(new ResponseObject("failed","file must be an image",""));
        }
        String uniqueFileName = UUID.randomUUID()+"";
        InputStream inputStream = file.getInputStream();
        try {
            switch (type){
                case 1:
                    return saveImage("User",inputStream,uniqueFileName);
                case 2:
                    return saveImage("Book",inputStream,uniqueFileName);
                case 3:
                    return saveImage("Paragraph",inputStream,uniqueFileName);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("failed",e.getMessage(),""));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseObject("failed","invalid id",""));
    }
    public ResponseEntity<ResponseObject> saveImage(String folderName, InputStream inputStream, String uniqueFileName){
        firebaseStorageService.uploadFile(folderName,inputStream,uniqueFileName);
        String url = "https://firebasestorage.googleapis.com/v0/b/web-springboot-1a3ab.appspot.com/o/LeetTruyen%2F"
                +folderName+"%2F"+uniqueFileName+"?alt=media";
        return ResponseEntity.ok(
                new ResponseObject("Success","upload image success",url)
        );
    }
}
