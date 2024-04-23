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
    @PostMapping(value = "/{id}"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseObject> uploadImages(
            @PathVariable Integer id,
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
        switch (id){
            case 1:
                firebaseStorageService.uploadFile("Book",inputStream,uniqueFileName);
                break;
            case 2:
                firebaseStorageService.uploadFile("Chapter",inputStream,uniqueFileName);
                break;
            case 3:
                firebaseStorageService.uploadFile("Paragraph",inputStream,uniqueFileName);
                break;
            default:
                return ResponseEntity.badRequest().body(
                        new ResponseObject("failed","invalid id","")
                );
        }
        inputStream.close();
        return ResponseEntity.ok(
                new ResponseObject("Success","upload image success",uniqueFileName)
        );
    }
}
