package com.cash.back_cash_manager.controllers;

import com.cash.back_cash_manager.config.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        JsonResponse response = new JsonResponse("test");
        return ResponseEntity.ok().body(response);
    }
}

//mvn clean install
//java -jar .\target\Back_Cash_Manager-0.0.1-SNAPSHOT.jar