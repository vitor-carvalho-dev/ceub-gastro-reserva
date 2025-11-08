package br.com.ceub.gastroreserva.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
// @EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Aplica a configuração a todos os endpoints que começam com /api/
                .allowedOrigins("http://localhost:3000") // Permite requisições desta origem (seu React app)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos na requisição
                .allowCredentials(true); // Permite o envio de cookies e credenciais de autenticação
    }

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia a URL pública /uploads/** para o diretório físico 'uploads/' na raiz do projeto.
        // O prefixo "file:" é essencial para indicar que é um caminho no sistema de arquivos.
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    } */
}
