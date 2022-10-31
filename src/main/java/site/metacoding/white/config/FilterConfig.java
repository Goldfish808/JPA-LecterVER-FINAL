package site.metacoding.white.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.white.config.auth.JwtAuthenticationFilter;
import site.metacoding.white.domain.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FilterConfig {

    private final UserRepository userRepository; // DI (스프링 IoC 컨테이너에서 옴)

    // IoC등록 (서버 실행시)
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegister() {
        log.debug("디버그 : 인증 필터 등록");
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthenticationFilter(userRepository));
        bean.addUrlPatterns("/login");
        return bean;
    }

}
