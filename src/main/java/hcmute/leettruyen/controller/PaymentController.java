package hcmute.leettruyen.controller;

import hcmute.leettruyen.entity.ResponseObject;
import hcmute.leettruyen.service.IPaymentMethodService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequestMapping("/api/v1/payment")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class PaymentController {

    private final IPaymentMethodService paymentMethodService;


//    @GetMapping("/NCB")
//    public ResponseEntity<ResponseObject> createPaymentNCB() {
//        try {
//            return ResponseEntity.ok(
//                    new ResponseObject("ok",
//                            "",
//                            paymentMethodService.createPayment(100000)));
//        } catch (UnsupportedEncodingException e) {
//            return ResponseEntity.badRequest().body(
//                    new ResponseObject("Fail", e.getMessage(), ""));
//        }
//    }

}
