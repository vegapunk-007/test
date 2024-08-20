package io.portone.controller;

import com.stripe.model.PaymentIntent;
import io.portone.exception.PortoneException;
import io.portone.model.PaymentIntentRequest;
import io.portone.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class A {
    @RestController
    public static class PaymentGatewayController {
        @Autowired
        private PaymentGatewayService paymentGatewayService;

        @PostMapping("/api/v1/create_intent")
        public ResponseEntity<String> createIntentForPaymentHandler(@RequestBody PaymentIntentRequest paymentIntentRequest) throws PortoneException {
            return new ResponseEntity<> (paymentGatewayService.createIntentForPayment(paymentIntentRequest), HttpStatus.ACCEPTED);
        }

        @PostMapping("/api/v1/capture_intent/{id}")
        public ResponseEntity<String> captureTheCreatedIntentHandler(@PathVariable("id") String paymentIntentId) throws PortoneException {
            return new ResponseEntity<> (paymentGatewayService.captureTheCreatedIntent(paymentIntentId), HttpStatus.ACCEPTED);
        }

        @PostMapping("/api/v1/create_refund/{id}")
        public ResponseEntity<String> createTheRefundByPaymentIntentHandler(@PathVariable("id") String paymentIntentId) throws PortoneException {
            return new ResponseEntity<> (paymentGatewayService.createRefundForPaymentIntent(paymentIntentId), HttpStatus.ACCEPTED);
        }

        @GetMapping("/api/v1/get_intents")
        public ResponseEntity<List<PaymentIntent>> getAllTheIntentsHandler()throws PortoneException{
            return new ResponseEntity<>(paymentGatewayService.getAllTheIntent(), HttpStatus.OK);
        }
    }
}
