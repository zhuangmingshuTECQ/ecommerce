package com.mingshu.ecommerce.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig: WebMvcConfigurer {
    fun addCorsMapping(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:3000")
    }
}