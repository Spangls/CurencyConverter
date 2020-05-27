package ru.mpt.convertor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Collections;

@SpringBootApplication
public class ConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

    public ConverterApplication(FreeMarkerConfigurer configurer) {
        configurer.getTaglibFactory().setClasspathTlds(Collections.singletonList("/META-INF/security.tld"));
    }
}
