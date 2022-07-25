package com.finalproject.Rest;

import com.finalproject.Service.IPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
public class PaymentRestController {

    private final IPaymentService paymentService;

    public PaymentRestController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getAllDepts")
    public ResponseEntity<?> getPayments(){
        if(paymentService.getPayments().size()==0)
            return new ResponseEntity<>("Record is not found!!", HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok().body(paymentService.getPayments());
    }
    @PostMapping("/pay/{invoiceId}")
    public ResponseEntity<?> payInvoice(@PathVariable int invoiceId){
        try{
            if(paymentService.payInvoice(invoiceId))
                return ResponseEntity.ok("Invoice paid!");
            else
                return new ResponseEntity<>("Invoice already paid.",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Invoice is not found!",HttpStatus.NOT_FOUND);
        }
    }
}
