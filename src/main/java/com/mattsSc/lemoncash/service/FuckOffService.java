package com.mattsSc.lemoncash.service;

import com.mattsSc.lemoncash.connector.FuckOffConnector;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FuckOffService {

    private FuckOffConnector fuckOffConnector;

    @Autowired
    public FuckOffService(FuckOffConnector fuckOffConnector) {
        this.fuckOffConnector = fuckOffConnector;
    }


    public String getMessage(String company, String name, String from){
        log.info(String.format("FOAAS - Get mesage for company %s and name %s",company, name));
        return Try.of(() -> fuckOffConnector.getMessage(company, name, from))
                .onFailure(ex -> log.error("FOASS - Connection error" , ex))
                .get();
    }

    public String getMessage(String from){
        log.info("FOAAS - Get base message");
        return Try.of(() -> fuckOffConnector.getMessage(from))
                .onFailure(ex -> log.error("FOASS - Connection error" , ex))
                .get();
    }

    public String getMessageForCompany(String company, String from){
        log.info(String.format("FOAAS - Get mesage for company %s",company));
        return Try.of(() -> fuckOffConnector.getMessage(company, from, true))
                .onFailure(ex -> log.error("FOASS - Connection error" , ex))
                .get();
    }

    public String getMessageForName(String name, String from){
        log.info(String.format("FOAAS - Get mesage for name %s", name));
        return Try.of(() -> fuckOffConnector.getMessage(name, from, false))
                .onFailure(ex -> log.error("FOASS - Connection error" , ex))
                .get();
    }
}
