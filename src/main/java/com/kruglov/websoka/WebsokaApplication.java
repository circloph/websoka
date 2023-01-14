package com.kruglov.websoka;

import javax.servlet.MultipartConfigElement;
import javax.xml.crypto.Data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class WebsokaApplication {

	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("128KB"));
        factory.setMaxRequestSize(DataSize.parse("128KB"));
        return factory.createMultipartConfig();
    }

	public static void main(String[] args) {
		SpringApplication.run(WebsokaApplication.class, args);
	}

}
