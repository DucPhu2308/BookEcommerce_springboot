package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.PaymentMethodDto;
import hcmute.leettruyen.dto.UserTransactionDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IPaymentMethodService;
import hcmute.leettruyen.service.IUserTransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequestMapping("/api/v1/payment")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class PaymentController {

    private final IPaymentMethodService paymentMethodService;
    private final IUserTransactionService userTransactionService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createPayment(
            @Valid @RequestBody PaymentMethodDto paymentMethodDto,
            BindingResult result) throws UnsupportedEncodingException {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("failed", result.getFieldError().getDefaultMessage(),""));
        }
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            paymentMethodService.createPayment(paymentMethodDto.getAmount())));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }

    @PostMapping("/addPayment")
    public ResponseEntity<ResponseObject> addPayment(
            @Valid @RequestBody UserTransactionDto userTransactionDto,
            BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("failed", result.getFieldError().getDefaultMessage(),""));
        }
        try {
            return ResponseEntity.ok(
                    new ResponseObject("ok",
                            "",
                            userTransactionService.save(userTransactionDto)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new ResponseObject("Fail",e.getMessage(),""));
        }
    }

    @GetMapping("/getAllPaymentByUser/{userId}")
    public ResponseEntity<ResponseObject> getAllPayment(
            @PathVariable int userId){
        return ResponseEntity.ok(
                new ResponseObject("ok",
                        "",
                        userTransactionService.getAllTransactionByUser(userId)));
    }

}
