package com.mattsSc.lemoncash.controller;

import com.mattsSc.lemoncash.service.FuckOffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@Api(value = "/message", tags = { "Message controller" })
@RequestMapping("message")
public class MessageController {

    @Autowired
    private FuckOffService fuckOffService;

    @ApiOperation(value = "obtain message", notes = "Get message from FOAAS")
    @GetMapping
    public ResponseEntity<String> getMessageFromFOAAS(@RequestParam(required = false) String name, @RequestParam(required = false) String company) {
        log.info("Received request for get message");
        if(Objects.nonNull(name) && Objects.nonNull(company)){
            return new ResponseEntity(fuckOffService.getMessage(company, name, "user"), HttpStatus.OK);
        }

        if(Objects.nonNull(name)){
            return new ResponseEntity(fuckOffService.getMessageForName(name, "user"), HttpStatus.OK);
        }

        if(Objects.nonNull(company)){
            return new ResponseEntity(fuckOffService.getMessageForCompany(company, "user"), HttpStatus.OK);
        }
        log.info("End request for get message");
        return new ResponseEntity(fuckOffService.getMessage("user"), HttpStatus.OK);
    }

}
