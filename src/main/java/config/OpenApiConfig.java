package config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

@Configuration
@EnableOpenApi
public class OpenApiConfig {
    @Bean
    OpenAPI openAPI(){
    return new OpenAPI()
            .components(new Components().addSecuritySchemes("JWT token",
                    new SecurityScheme().type(SecurityScheme.Type.HTTP)
                            .scheme("bearer").bearerFormat("JWT")
                            .in(SecurityScheme.In.HEADER).name("Authorization")))
            .info(new Info().title("SHOP API").version("1.0"))
            .addSecurityItem(
                    new SecurityRequirement().addList("JWT token", Arrays.asList("read","write")));
    }
}
