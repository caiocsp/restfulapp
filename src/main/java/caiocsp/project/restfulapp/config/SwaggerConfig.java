package caiocsp.project.restfulapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Restful API", version = "1.0", description = "Task Management REST API"), servers = {
        @Server(url = "http://localhost:8081", description = "Local")
})
public class SwaggerConfig {

    @Autowired
    Environment environment;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
