package hcmute.leettruyen.controller;

import hcmute.leettruyen.dto.PaymentMethodDto;
import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IPaymentMethodService;
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

}
