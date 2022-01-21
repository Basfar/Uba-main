package com.example.ubamain.controller;

import com.example.ubamain.dto.DataResponse;
import com.example.ubamain.service.UbaService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UbaController {
    private final UbaService ubaService;

    public UbaController(UbaService ubaService) {
        this.ubaService = ubaService;
    }
    private static final Logger logger = LogManager.getLogger(UbaController.class);


    //HTTP call
    @GetMapping("/fetch")
    public ResponseEntity<?> fetchData(){
        try{
            DataResponse response = ubaService.fetchData();
            HashMap<Object, Object> jsonObject = new HashMap<>();
            jsonObject.put("smsTotalReceived", 0);
            jsonObject.put("smsTotalSuccess", 0);
            jsonObject.put("smsTotalFailed", 0);
            jsonObject.put("smsTotalPending", 0);
            jsonObject.put("emailTotalReceived", 0);
            jsonObject.put("emailTotalSuccess", 0);
            jsonObject.put("emailTotalFailed", 0);
            jsonObject.put("emailTotalPending", 0);
            jsonObject.put("socialTotalReceived", 0);
            jsonObject.put("socialTotalSuccess", 0);
            jsonObject.put("socialTotalFailed", 0);
            jsonObject.put("socialTotalPending", 0);

            logger.log(Level.INFO, "get data request initiated, Time: {}", LocalDateTime.now());
            logger.log(Level.INFO,"get data response sent: Time: {}",LocalDateTime.now());
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.INFO,"===Exception in BusinessController-CustomException===");
            logger.log(Level.INFO,e.getMessage());
//                JSONObject errorObj = HttpRestClient.getResponse(e.getCode(),e.getMessage());
            logger.log(Level.INFO,"get data response sent: Time: {}", LocalDateTime.now());
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }


    //websocket
    @MessageMapping("/data")
    @SendTo("/getData")
    public ResponseEntity<?> getData() throws InterruptedException {
        Thread.sleep(2000);
        try{
            DataResponse response = ubaService.fetchData();
            HashMap<Object, Object> jsonObject = new HashMap<>();
            jsonObject.put("smsTotalReceived", response.getSmsTotalReceived());
            jsonObject.put("smsTotalSuccess", response.getSmsTotalSuccess());
            jsonObject.put("smsTotalFailed", response.getSmsTotalFailed());
            jsonObject.put("smsTotalPending", response.getSmsTotalPending());
            jsonObject.put("emailTotalReceived", response.getEmailTotalReceived());
            jsonObject.put("emailTotalSuccess", response.getEmailTotalSuccess());
            jsonObject.put("emailTotalFailed", response.getEmailTotalFailed());
            jsonObject.put("emailTotalPending", response.getEmailTotalPending());
            jsonObject.put("socialTotalReceived", response.getSocialTotalReceived());
            jsonObject.put("socialTotalSuccess", response.getSocialTotalSuccess());
            jsonObject.put("socialTotalFailed", response.getSocialTotalFailed());
            jsonObject.put("socialTotalPending", response.getSocialTotalPending());
            logger.log(Level.INFO, "get data request initiated, Time: {}", LocalDateTime.now());
            logger.log(Level.INFO,"get data response sent: Time: {}",LocalDateTime.now());
            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.INFO,"===Exception in BusinessController-CustomException===");
            logger.log(Level.INFO,e.getMessage());
//                JSONObject errorObj = HttpRestClient.getResponse(e.getCode(),e.getMessage());
            logger.log(Level.INFO,"get data response sent: Time: {}", LocalDateTime.now());
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }
}
