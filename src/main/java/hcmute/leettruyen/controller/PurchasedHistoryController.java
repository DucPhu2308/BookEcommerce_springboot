package hcmute.leettruyen.controller;

import hcmute.leettruyen.entity.PurchasedHistory;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.response.PurchasedHistoryResponse;
import hcmute.leettruyen.service.IPurchasedService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchased-history")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class PurchasedHistoryController {
    private final IPurchasedService purchasedService;
    private final ModelMapper modelMapper;
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getPurchasedHistory(){
        try {
            List<PurchasedHistory> purchasedHistories = purchasedService.findByCrtUser();

            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",purchasedHistories.stream()
                            .map(purchasedHistory -> modelMapper.map(purchasedHistory, PurchasedHistoryResponse.class))
                            .toList()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }
}
