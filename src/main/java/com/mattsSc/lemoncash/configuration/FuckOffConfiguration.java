package com.mattsSc.lemoncash.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "fuck-off")
public class FuckOffConfiguration {

    String baseUrl;
    String nameCompanyUrl;
    String companyUrl;
    String nameUrl;
    String fromUrl;

}
