package site.metacoding.white.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanInit {

    @Bean
    public ObjectMapper omInit() {
        return new ObjectMapper();
    }

    @Bean
    public HttpHeaders hhInit() {
        return new HttpHeaders();
    }
}
