package com.mattsSc.lemoncash.connector;

import com.mattsSc.lemoncash.api.response.FuckOffMessageResponse;
import com.mattsSc.lemoncash.configuration.FuckOffConfiguration;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class FuckOffConnector {

    private final FuckOffConfiguration configuration;
    private final RestTemplate restTemplate;

    public String getMessage(String company, String name, String from){
        throw new RuntimeException();
        //return callFuckOffService(String.format(configuration.getNameCompanyUrl(), name, company, from));
    }


    public String getMessage(String parameter, String from, boolean isCompany){

        String endpointUrl = isCompany ? configuration.getCompanyUrl() : configuration.getNameUrl();

        return callFuckOffService(String.format(endpointUrl, parameter, from));
    }

    public String getMessage(String from){
        return callFuckOffService(String.format(configuration.getFromUrl(), from));
    }


    private String callFuckOffService(String endpointUrl) {
        ResponseEntity<FuckOffMessageResponse> response = restTemplate.exchange(
                Strings.concat(configuration.getBaseUrl(), endpointUrl),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FuckOffMessageResponse>() {});

        return String.format("%s %s", Objects.requireNonNull(response.getBody()).message(), response.getBody().subtitle());
    }
}
