package hcmute.leettruyen.controller;

import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IPurchasedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchased-history")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PurchasedHistoryController {
    private final IPurchasedService purchasedService;
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getPurchasedHistory(){
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",purchasedService.findByCrtUser()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
