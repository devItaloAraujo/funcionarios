package com.funcerp.projetoerpfuncionarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOriginPatterns("http://localhost:*", "https://italo-funcerp.fly.dev")
        .allowedMethods("GET", "POST", "PATCH", "DELETE")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
