package com.example.paymentservice.controller;

import com.example.paymentservice.model.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

/*In application.properties of the app: server.port=9090*/
@RestController
public class PaymentsController {
    private static Logger logger = Logger.getLogger(PaymentsController.class.getName());

    /*
        curl -X POST http://localhost:9090/payment -H "Content-Type: application/json"
        -H "requestId: qwerty" -d "{\"id\": \"abcd\", \"amount\": 500}"
     */
    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(
            @RequestHeader String requestId,
            @RequestBody Payment payment) {
        logger.info("Receive request with ID " + requestId
                + "; Payment amount:" + payment.getAmount());
        payment.setId(UUID.randomUUID().toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("requestId", requestId)
                .body(payment);
    }
}
