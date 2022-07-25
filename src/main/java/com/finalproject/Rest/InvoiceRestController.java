package com.finalproject.Rest;

import com.finalproject.Entity.Invoice;
import com.finalproject.Service.IInvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/invoices")
public class InvoiceRestController {

    private final IInvoiceService InvoiceService;

    public InvoiceRestController(IInvoiceService invoiceService) {
        InvoiceService = invoiceService;
    }

    @PostMapping("/createInvoice/{user_id}")
    public ResponseEntity<?> addInvoice(@RequestBody Invoice invoice,@PathVariable int user_id){
        try{
            return ResponseEntity.ok().body(InvoiceService.addInvoice(invoice,user_id));
        }catch(Exception e){
            return new ResponseEntity<>("User is not found!",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteInvoice/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable int id){
        try{
            InvoiceService.deleteInvoice(id);
            return ResponseEntity.ok().body("Invoice is deleted!");
        }catch(Exception e){
            return new ResponseEntity<>("Invoice is not found!",HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/updateInvoice/{invoiceId}")
    public ResponseEntity<?> updateInvoice(@RequestBody Invoice invoice, @PathVariable int invoiceId){
        try{
            return ResponseEntity.ok().body(InvoiceService.updateInvoice(invoice,invoiceId));
        }catch(Exception e){
            return new ResponseEntity<>("Invoice is not found!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getInvoices")
    public ResponseEntity<?> getInvoiceList(){
        try{
            return ResponseEntity.ok().body(InvoiceService.getInvoiceList());
        }catch(Exception e){
            return new ResponseEntity<>("Invoices are not found!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getInvoice/{id}")
    public ResponseEntity<?> getInvoiceByInvoiceId(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(InvoiceService.getInvoiceByInvoiceId(id));
        }catch(Exception e){
            return new ResponseEntity<>("Invoice is not found!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}/invoice/{invoiceId}")
    public ResponseEntity<?> isPaidInvoice(@PathVariable int userId, @PathVariable int invoiceId){

        try{
            if (!InvoiceService.isPaidInvoice(userId, invoiceId))
                return ResponseEntity.ok().body("Invoice did not paid yet!");
            else
                return ResponseEntity.ok().body("Invoice has been paid!");
        }catch(Exception e){
            return new ResponseEntity<>("Invoice is not found!", HttpStatus.NOT_FOUND);

        }


        }
    }

