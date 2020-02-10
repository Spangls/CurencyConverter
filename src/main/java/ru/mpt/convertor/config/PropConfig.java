package ru.mpt.convertor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:converter.properties")
public class PropConfig {
    @Value("${rateUrl}")
    private String rateUrl;
    @Value("${dateFormat}")
    private String dateFormat;


}
